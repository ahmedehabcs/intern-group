import { HttpErrorResponse } from '@angular/common/http';
import { Observable, of, throwError, timer } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { environment } from '../../environments/environment';

export function cloneMock<T>(value: T): T {
  return structuredClone(value);
}

export function mockHttpError(status: number, message: string): HttpErrorResponse {
  return new HttpErrorResponse({ status, error: { error: message, message } });
}

export function mockResponse<T>(factory: () => T): Observable<T> {
  return timer(environment.mock.delayMs).pipe(
    mergeMap(() =>
      environment.mock.forceError
        ? throwError(() => mockHttpError(500, 'Forced mock error'))
        : of(cloneMock(factory())),
    ),
  );
}

export function mockFailure<T>(status: number, message: string): Observable<T> {
  return timer(environment.mock.delayMs).pipe(
    mergeMap(() => throwError(() => mockHttpError(status, message))),
  );
}
