import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, shareReplay } from 'rxjs';
import { environment } from '../../../environments/environment';
import { MockDataStore } from '../../mocks/mock-data.store';
import { GovernorateResponse } from '../models/governorate.model';

@Injectable({ providedIn: 'root' })
export class GovernorateService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  private api = `${environment.apiUrl}/api/governorates`;

  // Reference data that does not change while the app is open, and two
  // separate forms ask for it, so the response is cached for the session
  // rather than refetched per form.
  private cached?: Observable<GovernorateResponse[]>;

  list(): Observable<GovernorateResponse[]> {
    this.cached ??= (
      environment.mock.enabled
        ? this.mock.governorates()
        : this.http.get<GovernorateResponse[]>(this.api)
    ).pipe(shareReplay({ bufferSize: 1, refCount: false }));

    return this.cached;
  }
}
