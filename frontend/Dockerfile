# syntax=docker/dockerfile:1

# --- build stage -------------------------------------------------------------
FROM node:24-alpine AS build
WORKDIR /build

# package-lock.json is committed, so `npm ci` gives a reproducible install.
# Copying the manifests alone first keeps the install in a cacheable layer.
COPY package.json package-lock.json ./
RUN npm ci

COPY . .
# angular.json sets defaultConfiguration: production, so `npm run build` already
# applies the production file replacement and budgets.
RUN npm run build

# --- runtime stage -----------------------------------------------------------
FROM nginx:1.27-alpine AS runtime

# The @angular/build:application builder emits browser assets into a browser/
# subdirectory beneath the output path, i.e. dist/talabat-frontend/browser.
COPY --from=build /build/dist/talabat-frontend/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
