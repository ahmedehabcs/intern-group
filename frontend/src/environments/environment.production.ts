export const environment = {
  production: true,
  // Empty on purpose. Services build URLs as `${environment.apiUrl}/api/...`,
  // so this yields relative paths like /api/auth/login - the browser sends them
  // to whatever origin served the app, and nginx proxies /api/ to the backend
  // (see frontend/nginx.conf).
  //
  // The practical win: the same image runs on any host or domain. Hardcoding an
  // absolute URL here would bake one server's address into the bundle at build
  // time and reintroduce cross-origin requests.
  apiUrl: '',
} as const;
