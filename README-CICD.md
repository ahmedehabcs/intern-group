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

To run only the database and keep the apps in your IDE:

```bash
docker compose up postgres
```

## Adding a real deploy

CD currently publishes images and stops — the `deploy` job is a placeholder
because the project has no hosting target yet. The images are host-agnostic:

```
ghcr.io/ahmedehabcs/intern-group-backend:latest
ghcr.io/ahmedehabcs/intern-group-frontend:latest
```

Both are also tagged with the full commit SHA, so a deploy can pin an exact
build and roll back by re-pinning.

Wiring up a target means replacing the `deploy` job in `cd.yml`:

- **Render / Railway / Fly.io** — point the service at the GHCR image and hit
  its deploy hook with `curl`. Fastest path for an intern project.
- **Azure** — `azure/webapps-deploy@v3` with a publish profile, or
  `az containerapp update`, using `azure/login@v2` and an `AZURE_CREDENTIALS`
  secret.
- **AWS** — push to ECR instead of GHCR (`aws-actions/amazon-ecr-login@v2`),
  then `aws ecs update-service`.
- **Any VPS** — `appleboy/ssh-action` running `docker compose pull && docker
  compose up -d`.

Whichever you pick, remember the frontend's API URL is **baked in at build
time** from `src/environments/environment.production.ts`, which currently points
at `http://localhost:8080`. That has to change to the deployed API origin before
the frontend image is useful anywhere but a developer machine — and
`CORS_ALLOWED_ORIGINS` on the backend has to include the deployed frontend
origin, or the browser blocks every call.
