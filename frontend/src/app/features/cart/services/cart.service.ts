import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { AddCartItemRequest, CartResponse, ReplaceCartItemRequest } from '../models/cart.models';
import { MockDataStore } from '../../../mocks/mock-data.store';
@Injectable({ providedIn: 'root' })
export class CartService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  private api = `${environment.apiUrl}/api/cart`;
  readonly cart = signal<CartResponse | null>(null);
  readonly cartCount = computed(() => this.cart()?.items.reduce((s, i) => s + i.quantity, 0) ?? 0);
  load(): Observable<CartResponse> {
    return (environment.mock.enabled ? this.mock.cart() : this.http.get<CartResponse>(this.api)).pipe(tap((v) => this.cart.set(v)));
  }
  add(b: AddCartItemRequest): Observable<CartResponse> {
    return (environment.mock.enabled ? this.mock.addCartItem(b) : this.http.post<CartResponse>(`${this.api}/items`, b)).pipe(tap((v) => this.cart.set(v)));
  }
  quantity(id: number, quantity: number): Observable<CartResponse> {
    return (environment.mock.enabled ? this.mock.cartQuantity(id, quantity) : this.http
      .patch<CartResponse>(`${this.api}/items/${id}/quantity`, { quantity })
    ).pipe(tap((v) => this.cart.set(v)));
  }
  replace(id: number, b: ReplaceCartItemRequest): Observable<CartResponse> {
    return (environment.mock.enabled ? this.mock.replaceCartItem(id, b) : this.http
      .put<CartResponse>(`${this.api}/items/${id}`, b)
    ).pipe(tap((v) => this.cart.set(v)));
  }
  remove(id: number): Observable<CartResponse> {
    return (environment.mock.enabled ? this.mock.removeCartItem(id) : this.http.delete<CartResponse>(`${this.api}/items/${id}`)).pipe(tap((v) => this.cart.set(v)));
  }
  clear(): Observable<void> {
    return (environment.mock.enabled ? this.mock.clearCart() : this.http.delete<void>(`${this.api}/items`)).pipe(tap(() => this.cart.set(null)));
  }
}
