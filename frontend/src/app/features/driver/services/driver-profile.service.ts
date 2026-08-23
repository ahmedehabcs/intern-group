import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { DriverProfileResponse, DriverProfileUpdateRequest } from '../models/driver.models';
import { MockDataStore } from '../../../mocks/mock-data.store';
@Injectable({ providedIn: 'root' })
export class DriverProfileService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  private api = `${environment.apiUrl}/api/profile`;
  get(): Observable<DriverProfileResponse> {
    return environment.mock.enabled ? this.mock.driverProfile() : this.http.get<DriverProfileResponse>(this.api);
  }
  update(b: DriverProfileUpdateRequest): Observable<void> {
    return environment.mock.enabled ? this.mock.updateDriverProfile(b) : this.http.put<void>(this.api, b);
  }
}
