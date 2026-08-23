import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { MockDataStore } from '../../../mocks/mock-data.store';
import { DeliveryFeedbackResponse } from '../models/order.models';
@Injectable({ providedIn: 'root' })
export class DeliveryFeedbackService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  create(orderId: number, rating: number): Observable<DeliveryFeedbackResponse> {
    return environment.mock.enabled
      ? this.mock.createCustomerFeedback(orderId, rating)
      : this.http.post<DeliveryFeedbackResponse>(
          `${environment.apiUrl}/api/delivery-feedback/orders/${orderId}`,
          { rating },
        );
  }
}
