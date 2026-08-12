# syntax=docker/dockerfile:1

# --- build stage -------------------------------------------------------------
# The Maven image is used instead of the committed ./mvnw wrapper on purpose:
# the wrapper is configured with distributionType=only-script, so it would have
# to download Maven 3.9.16 over the network on every build.
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build

# Copy the POM alone first so the dependency download lands in its own layer and
# is reused whenever only application source has changed.
COPY pom.xml .
RUN mvn -B --no-transfer-progress dependency:go-offline

COPY src ./src
# Tests run in CI against a real Postgres service (see .github/workflows/ci.yml).
# Repeating them here would need a database inside the image build, so skip.
RUN mvn -B --no-transfer-progress clean package -DskipTests

# --- runtime stage -----------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Run as a non-root user. The JRE image defaults to root, which a container
# escape would inherit.
RUN addgroup -S app && adduser -S -G app app

# spring-boot-maven-plugin repackages into a single executable jar and excludes
# devtools automatically, so this is the only artifact needed at runtime.
COPY --from=build /build/target/*.jar app.jar

USER app
EXPOSE 8080

# Container memory limits are respected by default on JDK 10+, so no -Xmx here.
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
