import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { MockDataStore } from '../../../mocks/mock-data.store';
import { DeliveryFeedbackResponse } from '../models/driver.models';
@Injectable({ providedIn: 'root' })
export class DeliveryFeedbackService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  private api = `${environment.apiUrl}/api/delivery-feedback`;
  mine(): Observable<DeliveryFeedbackResponse[]> {
    return environment.mock.enabled
      ? this.mock.driverFeedback()
      : this.http.get<DeliveryFeedbackResponse[]>(`${this.api}/me`);
  }
  create(orderId: number, rating: number): Observable<DeliveryFeedbackResponse> {
    return environment.mock.enabled
      ? this.mock.createDriverFeedback(orderId, rating)
      : this.http.post<DeliveryFeedbackResponse>(`${this.api}/orders/${orderId}`, { rating });
  }
}
