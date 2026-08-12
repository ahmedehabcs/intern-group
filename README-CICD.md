# CI/CD

Two GitHub Actions workflows, plus containers for both apps.

| Workflow | Trigger | What it does |
| --- | --- | --- |
| `.github/workflows/ci.yml` | every PR, push to `main`, manual | Backend build + tests against a real Postgres; frontend production build; Prettier check (advisory) |
| `.github/workflows/cd.yml` | after CI succeeds on `main`, manual | Builds both Docker images and pushes them to GHCR |

Nothing needs configuring to start using this — CD authenticates with the
built-in `GITHUB_TOKEN`, so no registry account or secret setup is required.

## What CI actually checks

**Backend.** `./mvnw verify` on Temurin JDK 21, with a `postgres:16-alpine`
service container. The database is not optional: `BackendApplicationTests` is a
`@SpringBootTest`, so it boots the whole context, which runs all seven Flyway
migrations and then validates the JPA entity mappings against the resulting
schema (`spring.jpa.hibernate.ddl-auto=validate`). That combination is the most
valuable check in the pipeline — it catches an entity and a migration drifting
apart, which is the classic way this kind of project breaks.

The other two test classes (`CategoryServiceImplTests`, `CategoryModelTests`)
are plain Mockito unit tests and need nothing.

**Frontend.** `npm ci` then `npm run build`. The build is the only real gate,
because the project has **no test runner and zero `.spec.ts` files** —
`angular.json` sets `skipTests` for every schematic. `ng build` still
type-checks all TypeScript and compiles every template, so it is a genuine
check, just not a behavioural one. To add real tests:

```bash
cd frontend
ng add @analogjs/vitest-angular    # or: ng add @angular/build --karma
```

then add a `test` script and a step in `ci.yml` after the build.

**Formatting.** `npx prettier --check .` runs as a separate `continue-on-error`
job. It reports but cannot fail the build, because the repository currently has
**122 unformatted files** — enforcing it now would turn every PR red for reasons
unrelated to that PR. To start enforcing:

```bash
cd frontend
npx prettier --write .
git commit -am "style: apply Prettier across the frontend"
```

then delete the `continue-on-error: true` line from the `formatting` job.

## Two repository issues this pipeline works around

**`mvnw` is committed non-executable** (file mode `100644`), so `./mvnw` fails
with "Permission denied" on Linux runners. `ci.yml` runs `chmod +x ./mvnw` to
compensate. The permanent fix, worth doing once:

```bash
git update-index --chmod=+x backend/mvnw
git commit -m "chore: mark mvnw executable"
```

After that the `chmod` step in `ci.yml` is redundant and can be removed.

**`JAVA_HOME` on at least one dev machine points at JDK 1.8.** Maven then runs
on Java 8 and cannot compile this Java 21 project, failing with
`class file has wrong version 61.0, should be 52.0`. CI is unaffected because
`actions/setup-java` pins Temurin 21 explicitly, but local builds need
`JAVA_HOME` repointed at a JDK 21+ installation.

## Secrets

`backend/src/main/resources/application.properties` no longer contains
credentials. Every value now reads from the environment with a local-development
default:

| Variable | Required in production | Notes |
| --- | --- | --- |
| `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` | yes | defaults to local Postgres |
| `JWT_SECRET` | **yes** | Base64, must decode to ≥32 bytes: `openssl rand -base64 32` |
| `CORS_ALLOWED_ORIGINS` | **yes** | must list the deployed frontend origin |
| `MAIL_USERNAME`, `MAIL_PASSWORD` | if OTP is on | Gmail app password, not the account password |
| `OTP_ENABLED` | no | set `false` to run without SMTP |

See `.env.example` for the full list.

> **The previous values are still in git history.** Removing them from the
> working tree does not remove them from past commits, and every contributor's
> clone still contains them. The Gmail app password, the JWT signing key, and
> the database password all need to be **rotated** — revoke the Gmail app
> password at <https://myaccount.google.com/apppasswords> and generate a fresh
> JWT secret. Rotating is what actually fixes this; purging history is optional
> cleanup on top (`git filter-repo`, then every contributor re-clones).

To supply real values in CI, add repository secrets under
**Settings → Secrets and variables → Actions**. `ci.yml` already picks up
`JWT_SECRET` if present and falls back to a test-only literal otherwise, so fork
PRs — which never receive secrets — still run.

## Local development

```bash
cp .env.example .env      # fill in as needed
docker compose up --build
```

Frontend on <http://localhost:4200>, API on <http://localhost:8080>, Swagger at
<http://localhost:8080/swagger-ui.html>.

## Running just the database in Docker

This is the common case — database in a container, apps in your IDE:

```bash
docker compose up -d postgres     # start
docker compose ps                 # check health
docker compose logs -f postgres   # tail logs
docker compose stop postgres      # stop, keeps data
```

The container is `postgres:16-alpine`. On first start it creates the
`talabaty_db` database, and the first application run applies all seven Flyway
migrations, seed data included — there is nothing to import by hand.

Data lives in the named volume `intern-group_postgres-data` and survives
`stop`, `down`, and container recreation. To deliberately start clean:

```bash
docker compose down -v            # -v also deletes the volume
```

### The port 5432 conflict

Several machines on this team run a **natively installed PostgreSQL** as a
Windows service, which already owns port 5432. Publishing the container on the
same port fails with `port is already allocated`. So the host port is
configurable via `DB_PORT`, and the committed `.env` sets it to **5433** — the
container and the native service coexist, and no system change is needed.

This only affects reaching the database *from the host*. Inside the compose
network the backend container always talks to `postgres:5432`, so
`docker compose up` is unaffected either way.

Consequence worth knowing: `application.properties` still defaults to
`localhost:5432`, which is the **native** server. Running the backend from your
IDE therefore keeps using the native database unless you point it at the
container:

```
DB_URL=jdbc:postgresql://localhost:5433/talabaty_db
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

Set those in the IntelliJ run configuration's environment variables. Spring does
not read the root `.env` file — only `docker compose` does.

If you would rather have the container simply *be* the database on 5432, stop
the native service and set `DB_PORT=5432` in `.env`:

```powershell
# elevated PowerShell
Stop-Service postgresql-x64-18
Set-Service postgresql-x64-18 -StartupType Manual   # stop it auto-starting
```

Existing data in the native server is untouched by this — the service can be
started again at any time.

## Deploying to your server

**API and database only — the frontend is not deployed.** The server runs two
containers and needs **no JDK, no Node, and no source code**, only Docker and a
compose file. It pulls the image CD already built.

### How requests flow

```
browser (frontend, running elsewhere)
   │  cross-origin call
   ▼
:8080 backend container ──▶ postgres:5432   (no published port, private)
```

The database has no published port and is unreachable from the internet. The
API is published because nothing proxies for it.

Two consequences of dropping the frontend from the deployment:

- **CORS is load-bearing again.** With no same-origin nginx in front, every
  browser call to this API is cross-origin, so `CORS_ALLOWED_ORIGINS` must list
  the exact origin the Angular app is served from — scheme, host and port, no
  trailing slash. Get it wrong and the browser blocks every request, usually
  surfacing as a vague network error rather than anything mentioning CORS.
- **The API is directly exposed**, so the JWT secret and TLS matter more than
  they would behind a proxy. See the TLS section below.

`frontend/nginx.conf` still contains the `/api` proxy and
`environment.production.ts` still uses a relative `apiUrl`. Neither is dead
weight: they are what the local `docker compose up` stack uses, and they are
what you will want the day the frontend does get deployed. Local `ng serve` is
unaffected — it uses `environment.development.ts`.

To point a locally-served frontend at this server's API, set the `apiUrl` in
`src/environments/environment.development.ts` to `http://193.181.217.106:8080`
and add `http://localhost:4200` to `CORS_ALLOWED_ORIGINS` on the server.

### One-time server setup

```bash
# 1. Install Docker (Ubuntu/Debian)
curl -fsSL https://get.docker.com | sh
sudo usermod -aG docker $USER && newgrp docker

# 2. Create the deploy directory
sudo mkdir -p /opt/talabaty && sudo chown $USER:$USER /opt/talabaty
cd /opt/talabaty

# 3. Put the compose file and secrets in place
#    (from your laptop)
#      scp docker-compose.prod.yml user@server:/opt/talabaty/
#      scp .env.production.example  user@server:/opt/talabaty/.env
nano .env          # fill in every value
chmod 600 .env     # it holds secrets

# 4. Log in to GHCR (packages are private by default)
echo "$GITHUB_PAT" | docker login ghcr.io -u YOUR_GITHUB_USERNAME --password-stdin

# 5. Start
docker compose -f docker-compose.prod.yml pull
docker compose -f docker-compose.prod.yml up -d
docker compose -f docker-compose.prod.yml ps

# 6. Prove it works. /v3/api-docs is one of the few endpoints SecurityConfig
#    permits without a token, so a 200 here means the app is genuinely up.
curl -i http://localhost:8080/v3/api-docs
```

Note that most endpoints — including `/api/customer/categories` — answer **403**
without a JWT. `SecurityConfig` permits only `/api/auth/**`, `/swagger-ui/**`
and `/v3/api-docs/**`; everything else is `authenticated()`. A 403 from those is
the app working, not the deploy failing.

The database starts empty; the backend applies all seven Flyway migrations on
first boot, seed data included. Nothing to import.

Open the firewall — **port 8080 is currently closed on this server**, so the API
would be unreachable from outside without this:

```bash
sudo ufw allow 22/tcp && sudo ufw allow 8080/tcp && sudo ufw enable
```

### One-time server setup

```bash
# 1. Install Docker (Ubuntu/Debian)
curl -fsSL https://get.docker.com | sh
sudo usermod -aG docker $USER && newgrp docker

# 2. Create the deploy directory
sudo mkdir -p /opt/talabaty && sudo chown $USER:$USER /opt/talabaty
cd /opt/talabaty

# 3. Put the compose file and secrets in place
#    (from your laptop)
#      scp docker-compose.prod.yml user@server:/opt/talabaty/
#      scp .env.production.example  user@server:/opt/talabaty/.env
nano .env          # fill in every value
chmod 600 .env     # it holds secrets

# 4. Log in to GHCR (packages are private by default)
echo "$GITHUB_PAT" | docker login ghcr.io -u YOUR_GITHUB_USERNAME --password-stdin

# 5. Start
docker compose -f docker-compose.prod.yml pull
docker compose -f docker-compose.prod.yml up -d
docker compose -f docker-compose.prod.yml ps
```

The database starts empty; the backend applies all seven Flyway migrations on
first boot, seed data included. Nothing to import.

Open the firewall for HTTP only:

```bash
sudo ufw allow 22/tcp && sudo ufw allow 80/tcp && sudo ufw enable
```

### Automatic deploys

Add these under **Settings → Secrets and variables → Actions** and the `deploy`
job in `cd.yml` starts working. Until `SSH_HOST` exists it skips itself, so
nothing breaks in the meantime.

| Secret | Value |
| --- | --- |
| `SSH_HOST` | server IP or hostname |
| `SSH_USER` | the deploy user |
| `SSH_KEY` | **private** key whose public half is in the server's `~/.ssh/authorized_keys` |
| `SSH_PORT` | optional, defaults to 22 |
| `DEPLOY_PATH` | optional, defaults to `/opt/talabaty` |
| `GHCR_PULL_TOKEN` | a PAT with `read:packages` |
| `JWT_SECRET` | optional; CI falls back to a test key without it |

Then every push to `main` that passes CI copies the compose file up, pulls the
new images, restarts, and **curls `/api/customer/categories` until it answers**
— if the stack does not come up within 30s the job fails and prints the backend
logs, rather than going green on a crashlooping container.

### Rollback

Images are tagged with the full commit SHA as well as `latest`. To pin or roll
back, set `IMAGE_TAG` in the server's `.env` and restart:

```bash
IMAGE_TAG=sha-<commit>   # in .env
docker compose -f docker-compose.prod.yml up -d
```

### This project's server: 193.181.217.106

Probed from outside on 2026-08-12:

| Port | State | Notes |
| --- | --- | --- |
| 22 | open | SSH available for the deploy job |
| 80 | open | **already serving** — returns `503 Server Unavailable` |
| 443 | open | TLS handshake fails (no usable certificate) |
| 8080 | **closed** | must be opened — this is where the API is published |

Two things to sort out before the first deploy.

**1. Open 8080.** The API is published there and the port is currently filtered,
so nothing outside the server can reach it:

```bash
sudo ufw allow 8080/tcp
```

**2. Know what is on port 80.** The compose stack no longer wants port 80, so
the existing service is not in the way — but a 503 means some proxy (nginx,
Apache, Plesk, Traefik…) is running with nothing healthy behind it, which is
worth understanding before you add services to the same box:

```bash
sudo ss -tlnp | grep -E ':80|:443'      # what owns the ports
systemctl list-units --type=service --state=running | grep -Ei 'nginx|apache|httpd|plesk'
```

If that proxy turns out to be one you control, the tidier long-term setup is to
leave the API unpublished and have the existing nginx forward `/api/` to it —
that gets you TLS and a single entry point for free. Until then, publishing
8080 directly is the straightforward path.

### Before real traffic: add TLS

Port 8080 is plain HTTP, so **JWTs and passwords travel in clear text**. Fine
for an internal demo, not for anything real.

`https://193.181.217.106` — a bare IP — is the awkward case, and note the 443
handshake already fails today, so nothing is currently serving usable TLS. The
reliable fix is a **domain name** pointed at this server; after that, Caddy or
the existing nginx can obtain and renew Let's Encrypt certificates
automatically, and you would set `CORS_ALLOWED_ORIGINS` to the `https://` origin
of wherever the frontend is served. Let's Encrypt has more recently added
certificates for bare IP addresses through a short-lived-certificate profile,
but that path is newer and needs a compatible ACME client — confirm it works
before depending on it. A self-signed certificate is the other option and
produces a browser warning on every visit.

One trap while on plain HTTP: if the frontend is ever served over `https://`,
browsers block its calls to an `http://` API as mixed content. Keep both on
HTTP, or move both to HTTPS — not one of each.

### Operating it

```bash
docker compose -f docker-compose.prod.yml logs -f backend   # tail logs
docker compose -f docker-compose.prod.yml restart backend   # restart one service
docker compose -f docker-compose.prod.yml exec postgres \
  pg_dump -U "$DB_USERNAME" talabaty_db > backup-$(date +%F).sql   # back up
```

There is no scheduled backup — the data lives in the `postgres-data` volume and
survives restarts, but a `docker compose down -v` destroys it. Put the `pg_dump`
line in a cron job before this holds anything you care about.
