import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { CustomerProfileResponse, CustomerProfileUpdateRequest } from '../models/account.models';
import { MockDataStore } from '../../../mocks/mock-data.store';
@Injectable({ providedIn: 'root' })
export class ProfileService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  private api = `${environment.apiUrl}/api/profile`;
  get(): Observable<CustomerProfileResponse> {
    return environment.mock.enabled ? this.mock.profile() : this.http.get<CustomerProfileResponse>(this.api);
  }
  update(b: CustomerProfileUpdateRequest): Observable<void> {
    return environment.mock.enabled ? this.mock.updateProfile(b) : this.http.put<void>(this.api, b);
  }
}
