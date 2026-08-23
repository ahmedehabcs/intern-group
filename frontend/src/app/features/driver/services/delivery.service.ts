import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { OrderHistoryResponse, OrderSummaryResponse } from '../models/driver.models';
import { MockDataStore } from '../../../mocks/mock-data.store';
@Injectable({ providedIn: 'root' })
export class DeliveryService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  private api = `${environment.apiUrl}/api/delivery`;
  available(): Observable<OrderSummaryResponse[]> {
    return environment.mock.enabled
      ? this.mock.availableDeliveries()
      : this.http.get<OrderSummaryResponse[]>(`${this.api}/orders/available`);
  }
  active(): Observable<OrderSummaryResponse | null> {
    return environment.mock.enabled
      ? this.mock.activeDelivery()
      : this.http.get<OrderSummaryResponse | null>(`${this.api}/orders/active`);
  }
  history(): Observable<OrderHistoryResponse[]> {
    return environment.mock.enabled
      ? this.mock.deliveryHistory()
      : this.http.get<OrderHistoryResponse[]>(`${this.api}/orders/history`);
  }
  action(
    id: number,
    a: 'accept' | 'pickup' | 'deliver' | 'cancel',
  ): Observable<OrderSummaryResponse> {
    return environment.mock.enabled
      ? this.mock.deliveryAction(id, a)
      : this.http.put<OrderSummaryResponse>(`${this.api}/orders/${id}/${a}`, null);
  }
  status(_online: boolean): Observable<void> {
    // TODO(api-contract): UpdateStatusRequest exists, but the endpoint itself documents no body.
    return environment.mock.enabled
      ? this.mock.setDriverStatus(_online)
      : this.http.put<void>(`${this.api}/profile/status`, null);
  }
}
