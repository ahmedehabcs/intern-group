import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { OrderStatus } from '../../orders/models/order.models';
import {
  AdminCategoryResponse,
  AssignKitchenManagerRequest,
  CategoryRequest,
  CreateRestaurantRequest,
  CustomerAdminResponse,
  DeliveryFeedbackResponse,
  OrderAdminResponse,
  RestaurantAdminResponse,
  RiderAdminResponse,
} from '../models/admin.models';
import { MockDataStore } from '../../../mocks/mock-data.store';
@Injectable({ providedIn: 'root' })
export class AdminApiService {
  private readonly http = inject(HttpClient);
  private readonly mock = inject(MockDataStore);
  private readonly api = `${environment.apiUrl}/api/admin`;
  categories(): Observable<AdminCategoryResponse[]> {
    return environment.mock.enabled
      ? this.mock.adminCategories()
      : this.http.get<AdminCategoryResponse[]>(`${this.api}/categories`);
  }
  createCategory(b: CategoryRequest): Observable<AdminCategoryResponse> {
    return environment.mock.enabled
      ? this.mock.createAdminCategory(b)
      : this.http.post<AdminCategoryResponse>(`${this.api}/categories`, b);
  }
  updateCategory(id: number, b: CategoryRequest): Observable<AdminCategoryResponse> {
    return environment.mock.enabled
      ? this.mock.updateAdminCategory(id, b)
      : this.http.put<AdminCategoryResponse>(`${this.api}/categories/${id}`, b);
  }
  deleteCategory(id: number): Observable<void> {
    return environment.mock.enabled
      ? this.mock.deleteAdminCategory(id)
      : this.http.delete<void>(`${this.api}/categories/${id}`);
  }
  customers(search?: string): Observable<CustomerAdminResponse[]> {
    return environment.mock.enabled
      ? this.mock.adminCustomers(search)
      : this.http.get<CustomerAdminResponse[]>(`${this.api}/customers`, {
          params: search ? new HttpParams().set('search', search) : undefined,
        });
  }
  orders(
    f: { status?: OrderStatus; restaurantId?: number; from?: string; to?: string } = {},
  ): Observable<OrderAdminResponse[]> {
    if (environment.mock.enabled) return this.mock.adminOrders(f);
    let p = new HttpParams();
    Object.entries(f).forEach(([k, v]) => {
      if (v !== undefined && v !== '') p = p.set(k, String(v));
    });
    return this.http.get<OrderAdminResponse[]>(`${this.api}/orders`, { params: p });
  }
  cancelOrder(id: number): Observable<OrderAdminResponse> {
    return environment.mock.enabled
      ? this.mock.cancelAdminOrder(id)
      : this.http.put<OrderAdminResponse>(`${this.api}/orders/${id}/cancel`, null);
  }
  restaurants(search?: string): Observable<RestaurantAdminResponse[]> {
    return environment.mock.enabled
      ? this.mock.adminRestaurants(search)
      : this.http.get<RestaurantAdminResponse[]>(`${this.api}/restaurants`, {
          params: search ? new HttpParams().set('search', search) : undefined,
        });
  }
  createRestaurant(b: CreateRestaurantRequest): Observable<RestaurantAdminResponse> {
    return environment.mock.enabled
      ? this.mock.createAdminRestaurant(b)
      : this.http.post<RestaurantAdminResponse>(`${this.api}/restaurants/add`, b);
  }
  editRestaurant(id: number): Observable<RestaurantAdminResponse> {
    return environment.mock.enabled
      ? this.mock.adminRestaurant(id)
      : this.http.put<RestaurantAdminResponse>(`${this.api}/restaurants/${id}/edit`, null);
  } // TODO(api-contract): endpoint documents no body despite an update DTO.
  updateRestaurantStatus(id: number, active: boolean): Observable<RestaurantAdminResponse> {
    return environment.mock.enabled
      ? this.mock.updateAdminRestaurantStatus(id, active)
      : this.http.put<RestaurantAdminResponse>(this.api + '/restaurants/' + id + '/status', null, {
          params: new HttpParams().set('active', String(active)),
        });
  }
  assignManager(id: number, b: AssignKitchenManagerRequest): Observable<void> {
    return environment.mock.enabled
      ? this.mock.assignManager(id, b)
      : this.http.post<void>(`${this.api}/restaurants/${id}/kitchen-manager`, b);
  }
  riders(search?: string): Observable<RiderAdminResponse[]> {
    return environment.mock.enabled
      ? this.mock.adminRiders(search)
      : this.http.get<RiderAdminResponse[]>(`${this.api}/riders`, {
          params: search ? new HttpParams().set('search', search) : undefined,
        });
  }
  pendingRiders(): Observable<RiderAdminResponse[]> {
    return environment.mock.enabled
      ? this.mock.pendingRiders()
      : this.http.get<RiderAdminResponse[]>(`${this.api}/riders/pending`);
  }
  riderAction(
    id: number,
    action: 'approve' | 'reject' | 'deactivate',
  ): Observable<RiderAdminResponse> {
    return environment.mock.enabled
      ? this.mock.riderAction(id, action)
      : this.http.put<RiderAdminResponse>(`${this.api}/riders/${id}/${action}`, null);
  }
  feedback(): Observable<DeliveryFeedbackResponse[]> {
    return environment.mock.enabled
      ? this.mock.adminFeedback()
      : this.http.get<DeliveryFeedbackResponse[]>(
          `${environment.apiUrl}/api/delivery-feedback/admin/all`,
        );
  }
}
