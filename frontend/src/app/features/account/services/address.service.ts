import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { MockDataStore } from '../../../mocks/mock-data.store';
import { AddressRequest, AddressResponse } from '../models/account.models';
@Injectable({ providedIn: 'root' })
export class AddressService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  private api = `${environment.apiUrl}/api/addresses`;
  list(): Observable<AddressResponse[]> {
    return environment.mock.enabled
      ? this.mock.addresses()
      : this.http.get<AddressResponse[]>(this.api);
  }
  get(id: number): Observable<AddressResponse> {
    return environment.mock.enabled
      ? this.mock.address(id)
      : this.http.get<AddressResponse>(`${this.api}/${id}`);
  }
  create(b: AddressRequest): Observable<AddressResponse> {
    return environment.mock.enabled
      ? this.mock.createAddress(b)
      : this.http.post<AddressResponse>(this.api, b);
  }
  update(id: number, b: AddressRequest): Observable<AddressResponse> {
    return environment.mock.enabled
      ? this.mock.updateAddress(id, b)
      : this.http.put<AddressResponse>(`${this.api}/${id}`, b);
  }
  setDefault(id: number): Observable<AddressResponse> {
    return environment.mock.enabled
      ? this.mock.setDefaultAddress(id)
      : this.http.patch<AddressResponse>(`${this.api}/${id}/default`, null);
  }
  delete(id: number): Observable<void> {
    return environment.mock.enabled
      ? this.mock.deleteAddress(id)
      : this.http.delete<void>(`${this.api}/${id}`);
  }
}
