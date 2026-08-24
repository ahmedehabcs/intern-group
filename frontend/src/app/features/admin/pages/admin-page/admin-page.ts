import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { finalize, Observable } from 'rxjs';
import {
  AdminCategoryResponse,
  CustomerAdminResponse,
  DeliveryFeedbackResponse,
  OrderAdminResponse,
  RestaurantAdminResponse,
  RiderAdminResponse,
} from '../../models/admin.models';
import { AdminApiService } from '../../services/admin-api.service';
import { OrderStatus } from '../../../orders/models/order.models';
import { ConfirmationDialogService } from '../../../../shared/services/confirmation-dialog.service';
type Mode =
  'categories' | 'customers' | 'orders' | 'restaurants' | 'riders' | 'pending' | 'feedback';
@Component({
  selector: 'app-admin-page',
  imports: [ReactiveFormsModule],
  templateUrl: './admin-page.html',
})
export class AdminPage {
  private api = inject(AdminApiService);
  private route = inject(ActivatedRoute);
  private destroy = inject(DestroyRef);
  private confirmation = inject(ConfirmationDialogService);
  readonly mode = this.route.snapshot.data['mode'] as Mode;
  readonly categories = signal<AdminCategoryResponse[]>([]);
  readonly customers = signal<CustomerAdminResponse[]>([]);
  readonly orders = signal<OrderAdminResponse[]>([]);
  readonly restaurants = signal<RestaurantAdminResponse[]>([]);
  readonly riders = signal<RiderAdminResponse[]>([]);
  readonly feedback = signal<DeliveryFeedbackResponse[]>([]);
  readonly loading = signal(true);
  readonly processingId = signal<number | null>(null);
  readonly error = signal<string | null>(null);
  readonly editingCategoryId = signal<number | null>(null);
  readonly search = new FormControl('', { nonNullable: true });
  readonly orderFilters = new FormGroup({
    status: new FormControl<OrderStatus | ''>('', { nonNullable: true }),
    restaurantId: new FormControl<number | null>(null),
    from: new FormControl('', { nonNullable: true }),
    to: new FormControl('', { nonNullable: true }),
  });
  readonly categoryForm = new FormGroup({
    name: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(100)],
    }),
    description: new FormControl('', {
      nonNullable: true,
      validators: [Validators.maxLength(1000)],
    }),
  });
  readonly restaurantForm = new FormGroup({
    name: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    phone: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    address: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    governorateId: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(1)],
    }),
    description: new FormControl('', { nonNullable: true }),
    logoUrl: new FormControl('', { nonNullable: true }),
    deliveryFee: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(0)],
    }),
  });
  readonly managerForm = new FormGroup({
    restaurantId: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(1)],
    }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    password: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(8), Validators.maxLength(100)],
    }),
    name: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(100)],
    }),
    phoneNumber: new FormControl('', { nonNullable: true, validators: [Validators.maxLength(20)] }),
  });
  constructor() {
    this.load();
  }
  load(): void {
    this.loading.set(true);
    const req: Observable<unknown> = (
      this.mode === 'categories'
        ? this.api.categories()
        : this.mode === 'customers'
          ? this.api.customers(this.search.value.trim() || undefined)
          : this.mode === 'orders'
            ? this.api.orders({
                status: this.orderFilters.value.status || undefined,
                restaurantId: this.orderFilters.value.restaurantId || undefined,
                from: this.orderFilters.value.from || undefined,
                to: this.orderFilters.value.to || undefined,
              })
            : this.mode === 'restaurants'
              ? this.api.restaurants(this.search.value.trim() || undefined)
              : this.mode === 'riders'
                ? this.api.riders(this.search.value.trim() || undefined)
                : this.mode === 'pending'
                  ? this.api.pendingRiders()
                  : this.api.feedback()
    ) as Observable<unknown>;
    req
      .pipe(
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (value) => {
          if (this.mode === 'categories') this.categories.set(value as AdminCategoryResponse[]);
          else if (this.mode === 'customers') this.customers.set(value as CustomerAdminResponse[]);
          else if (this.mode === 'orders') this.orders.set(value as OrderAdminResponse[]);
          else if (this.mode === 'restaurants')
            this.restaurants.set(value as RestaurantAdminResponse[]);
          else if (this.mode === 'riders' || this.mode === 'pending')
            this.riders.set(value as RiderAdminResponse[]);
          else this.feedback.set(value as DeliveryFeedbackResponse[]);
        },
        error: () => this.error.set('Unable to load admin data.'),
      });
  }
  createCategory(): void {
    if (this.categoryForm.invalid) return;
    const value = this.categoryForm.getRawValue();
    const body = { name: value.name, description: value.description || undefined };
    const request = this.editingCategoryId()
      ? this.api.updateCategory(this.editingCategoryId()!, body)
      : this.api.createCategory(body);
    this.mutate(request, (created) => {
      this.categories.update((rows) =>
        this.editingCategoryId()
          ? rows.map((row) => (row.id === created.id ? created : row))
          : [...rows, created],
      );
      this.editingCategoryId.set(null);
      this.categoryForm.reset();
    });
  }
  editCategory(item: AdminCategoryResponse): void {
    this.editingCategoryId.set(item.id);
    this.categoryForm.patchValue(item);
  }
  async deleteCategory(id: number): Promise<void> {
    if (!(await this.confirmation.confirm('Delete this category?', 'Delete category', 'Delete')))
      return;
    this.mutate(this.api.deleteCategory(id), () =>
      this.categories.update((rows) => rows.filter((row) => row.id !== id)),
    );
  }
  createRestaurant(): void {
    if (this.restaurantForm.invalid) return;
    const value = this.restaurantForm.getRawValue();
    this.mutate(
      this.api.createRestaurant({
        ...value,
        description: value.description || undefined,
        logoUrl: value.logoUrl || undefined,
      }),
      (created) => {
        this.restaurants.update((rows) => [...rows, created]);
        this.restaurantForm.reset();
      },
    );
  }
  assignManager(): void {
    if (this.managerForm.invalid) return;
    const value = this.managerForm.getRawValue();
    this.mutate(
      this.api.assignManager(value.restaurantId, {
        email: value.email,
        password: value.password,
        name: value.name,
        phoneNumber: value.phoneNumber || undefined,
      }),
      () => this.managerForm.reset(),
    );
  }
  private mutate<T>(request: Observable<T>, next: (value: T) => void): void {
    this.processingId.set(-1);
    this.error.set(null);
    request
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({ next, error: () => this.error.set('The change could not be saved.') });
  }
  async riderAction(id: number, a: 'approve' | 'reject' | 'deactivate'): Promise<void> {
    if (
      (a === 'reject' || a === 'deactivate') &&
      !(await this.confirmation.confirm(`${a} this rider?`, 'Update rider', a))
    )
      return;
    this.processingId.set(id);
    this.api
      .riderAction(id, a)
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (v) => this.riders.update((rows) => rows.map((r) => (r.id === v.id ? v : r))),
        error: () => this.error.set('Rider could not be updated.'),
      });
  }
  async cancelOrder(id: number): Promise<void> {
    if (!(await this.confirmation.confirm('Cancel this order?', 'Cancel order', 'Cancel order')))
      return;
    this.processingId.set(id);
    this.api
      .cancelOrder(id)
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (v) => this.orders.update((rows) => rows.map((r) => (r.id === v.id ? v : r))),
        error: () => this.error.set('Order could not be cancelled.'),
      });
  }
  async updateRestaurantStatus(item: RestaurantAdminResponse): Promise<void> {
    const active = !item.isActive;
    const action = active ? 'Activate' : 'Deactivate';
    if (
      !(await this.confirmation.confirm(
        action + ' this restaurant?',
        action + ' restaurant',
        action,
      ))
    )
      return;
    this.processingId.set(item.id);
    this.api
      .updateRestaurantStatus(item.id, active)
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (v) => this.restaurants.update((rows) => rows.map((r) => (r.id === v.id ? v : r))),
        error: () => this.error.set('Restaurant status could not be updated.'),
      });
  }
}
