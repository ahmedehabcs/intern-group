import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { MockDataStore } from '../../../mocks/mock-data.store';
import {
  CategoryResponse,
  MenuItemDetailsResponse,
  RestaurantDetailsResponse,
  RestaurantResponse,
} from '../models/restaurant.models';
@Injectable({ providedIn: 'root' })
export class RestaurantService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  private api = `${environment.apiUrl}/api`;
  categories(): Observable<CategoryResponse[]> {
    return environment.mock.enabled
      ? this.mock.categories()
      : this.http.get<CategoryResponse[]>(`${this.api}/categories`);
  }
  restaurants(categoryId?: number): Observable<RestaurantResponse[]> {
    return environment.mock.enabled
      ? this.mock.restaurants(categoryId)
      : this.http.get<RestaurantResponse[]>(`${this.api}/restaurants`, {
          params: categoryId ? new HttpParams().set('categoryId', categoryId) : undefined,
        });
  }
  restaurant(id: number): Observable<RestaurantDetailsResponse> {
    return environment.mock.enabled
      ? this.mock.restaurant(id)
      : this.http.get<RestaurantDetailsResponse>(`${this.api}/restaurants/${id}`);
  }
  menuItem(id: number): Observable<MenuItemDetailsResponse> {
    return environment.mock.enabled
      ? this.mock.menuItem(id)
      : this.http.get<MenuItemDetailsResponse>(`${this.api}/menu-items/${id}`);
  }
}
