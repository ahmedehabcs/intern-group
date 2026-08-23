import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { OrderStatus } from '../../orders/models/order.models';
import {
  CreateAddonGroupRequest,
  CreateAddonRequest,
  CreateMenuItemRequest,
  CreateMenuSectionRequest,
  KitchenAddonGroupResponse,
  KitchenAddonResponse,
  KitchenDashboardSummaryResponse,
  KitchenMenuItemResponse,
  KitchenMenuSectionResponse,
  KitchenOrderDetailsResponse,
  KitchenOrderPageResponse,
  KitchenOrderSummaryResponse,
  UpdateAddonGroupRequest,
  UpdateAddonRequest,
  UpdateMenuItemRequest,
  UpdateMenuSectionRequest,
} from '../models/kitchen.models';
import { MockDataStore } from '../../../mocks/mock-data.store';
@Injectable({ providedIn: 'root' })
export class KitchenApiService {
  private readonly http = inject(HttpClient);
  private readonly mock = inject(MockDataStore);
  private readonly api = `${environment.apiUrl}/api/kitchen`;
  dashboard(): Observable<KitchenDashboardSummaryResponse> {
    return environment.mock.enabled ? this.mock.kitchenDashboard() : this.http.get<KitchenDashboardSummaryResponse>(`${this.api}/dashboard/summary`);
  }
  orders(): Observable<KitchenOrderSummaryResponse[]> {
    return environment.mock.enabled ? this.mock.kitchenOrders() : this.http.get<KitchenOrderSummaryResponse[]>(`${this.api}/orders`);
  }
  history(filters: {
    status?: OrderStatus;
    from?: string;
    to?: string;
    page?: number;
    size?: number;
    direction?: 'ASC' | 'DESC';
  }): Observable<KitchenOrderPageResponse> {
    if (environment.mock.enabled) return this.mock.kitchenHistory(filters);
    let p = new HttpParams();
    Object.entries(filters).forEach(([k, v]) => {
      if (v !== undefined && v !== '') p = p.set(k, String(v));
    });
    return this.http.get<KitchenOrderPageResponse>(`${this.api}/orders/history`, { params: p });
  }
  order(id: number): Observable<KitchenOrderDetailsResponse> {
    return environment.mock.enabled ? this.mock.kitchenOrder(id) : this.http.get<KitchenOrderDetailsResponse>(`${this.api}/orders/${id}`);
  }
  updateStatus(id: number, status: OrderStatus): Observable<KitchenOrderDetailsResponse> {
    return environment.mock.enabled ? this.mock.updateKitchenOrder(id, status) : this.http.patch<KitchenOrderDetailsResponse>(`${this.api}/orders/${id}/status`, {
      status,
    });
  }
  cancel(id: number, reason: string): Observable<KitchenOrderDetailsResponse> {
    return environment.mock.enabled ? this.mock.updateKitchenOrder(id, 'CANCELLED') : this.http.patch<KitchenOrderDetailsResponse>(`${this.api}/orders/${id}/cancel`, {
      reason,
    });
  }
  menuItems(): Observable<KitchenMenuItemResponse[]> {
    return environment.mock.enabled ? this.mock.kitchenItems() : this.http.get<KitchenMenuItemResponse[]>(`${this.api}/menu-items`);
  }
  createMenuItem(b: CreateMenuItemRequest): Observable<KitchenMenuItemResponse> {
    return environment.mock.enabled ? this.mock.createKitchenItem(b) : this.http.post<KitchenMenuItemResponse>(`${this.api}/menu-items`, b);
  }
  updateMenuItem(id: number, b: UpdateMenuItemRequest): Observable<KitchenMenuItemResponse> {
    return environment.mock.enabled ? this.mock.updateKitchenItem(id, b) : this.http.patch<KitchenMenuItemResponse>(`${this.api}/menu-items/${id}`, b);
  }
  availability(id: number, available: boolean): Observable<KitchenMenuItemResponse> {
    return environment.mock.enabled ? this.mock.updateKitchenItem(id, { available }) : this.http.patch<KitchenMenuItemResponse>(`${this.api}/menu-items/${id}/availability`, {
      available,
    });
  }
  deleteMenuItem(id: number): Observable<void> {
    return environment.mock.enabled ? this.mock.deleteKitchenItem(id) : this.http.delete<void>(`${this.api}/menu-items/${id}`);
  }
  sections(): Observable<KitchenMenuSectionResponse[]> {
    return environment.mock.enabled ? this.mock.kitchenSections() : this.http.get<KitchenMenuSectionResponse[]>(`${this.api}/menu-items/sections`);
  }
  createSection(b: CreateMenuSectionRequest): Observable<KitchenMenuSectionResponse> {
    return environment.mock.enabled ? this.mock.createKitchenSection(b) : this.http.post<KitchenMenuSectionResponse>(`${this.api}/menu-items/sections`, b);
  }
  updateSection(id: number, b: UpdateMenuSectionRequest): Observable<KitchenMenuSectionResponse> {
    return environment.mock.enabled ? this.mock.updateKitchenSection(id, b) : this.http.patch<KitchenMenuSectionResponse>(`${this.api}/menu-items/sections/${id}`, b);
  }
  groups(): Observable<KitchenAddonGroupResponse[]> {
    return environment.mock.enabled ? this.mock.kitchenGroups() : this.http.get<KitchenAddonGroupResponse[]>(`${this.api}/menu-items/addon-groups`);
  }
  createGroup(b: CreateAddonGroupRequest): Observable<KitchenAddonGroupResponse> {
    return environment.mock.enabled ? this.mock.createKitchenGroup(b) : this.http.post<KitchenAddonGroupResponse>(`${this.api}/menu-items/addon-groups`, b);
  }
  updateGroup(id: number, b: UpdateAddonGroupRequest): Observable<KitchenAddonGroupResponse> {
    return environment.mock.enabled ? this.mock.updateKitchenGroup(id, b) : this.http.patch<KitchenAddonGroupResponse>(
      `${this.api}/menu-items/addon-groups/${id}`,
      b,
    );
  }
  deleteGroup(id: number): Observable<void> {
    return environment.mock.enabled ? this.mock.deleteKitchenGroup(id) : this.http.delete<void>(`${this.api}/menu-items/addon-groups/${id}`);
  }
  addons(groupId: number): Observable<KitchenAddonResponse[]> {
    return environment.mock.enabled ? this.mock.kitchenAddons(groupId) : this.http.get<KitchenAddonResponse[]>(
      `${this.api}/menu-items/addon-groups/${groupId}/addons`,
    );
  }
  createAddon(groupId: number, b: CreateAddonRequest): Observable<KitchenAddonResponse> {
    return environment.mock.enabled ? this.mock.createKitchenAddon(groupId, b) : this.http.post<KitchenAddonResponse>(
      `${this.api}/menu-items/addon-groups/${groupId}/addons`,
      b,
    );
  }
  updateAddon(id: number, b: UpdateAddonRequest): Observable<KitchenAddonResponse> {
    return environment.mock.enabled ? this.mock.updateKitchenAddon(id, b) : this.http.patch<KitchenAddonResponse>(`${this.api}/menu-items/addons/${id}`, b);
  }
  deleteAddon(id: number): Observable<void> {
    return environment.mock.enabled ? this.mock.deleteKitchenAddon(id) : this.http.delete<void>(`${this.api}/menu-items/addons/${id}`);
  }
}
