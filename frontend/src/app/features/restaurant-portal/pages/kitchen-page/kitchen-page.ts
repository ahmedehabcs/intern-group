import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { finalize, forkJoin, Observable } from 'rxjs';
import { OrderStatus } from '../../../orders/models/order.models';
import {
  KitchenAddonGroupResponse,
  KitchenDashboardSummaryResponse,
  KitchenMenuItemResponse,
  KitchenMenuSectionResponse,
  KitchenOrderPageResponse,
  KitchenOrderSummaryResponse,
} from '../../models/kitchen.models';
import { KitchenApiService } from '../../services/kitchen-api.service';
import { ConfirmationDialogService } from '../../../../shared/services/confirmation-dialog.service';
import { StatusBadge } from '../../../../shared/components/status-badge/status-badge';
type Mode = 'dashboard' | 'orders' | 'history' | 'menu-items' | 'sections' | 'addon-groups';
interface KitchenMenuContext { items: KitchenMenuItemResponse[]; sections: KitchenMenuSectionResponse[]; groups: KitchenAddonGroupResponse[]; }
@Component({
  selector: 'app-kitchen-page',
  imports: [RouterLink, ReactiveFormsModule, StatusBadge],
  templateUrl: './kitchen-page.html',
})
export class KitchenPage {
  private api = inject(KitchenApiService);
  private route = inject(ActivatedRoute);
  private destroy = inject(DestroyRef);
  private confirmation = inject(ConfirmationDialogService);
  readonly mode = this.route.snapshot.data['mode'] as Mode;
  readonly dashboard = signal<KitchenDashboardSummaryResponse | null>(null);
  readonly orders = signal<KitchenOrderPageResponse | null>(null);
  readonly activeOrders = signal<KitchenOrderSummaryResponse[]>([]);
  readonly menuItems = signal<KitchenMenuItemResponse[]>([]);
  readonly sections = signal<KitchenMenuSectionResponse[]>([]);
  readonly groups = signal<KitchenAddonGroupResponse[]>([]);
  readonly loading = signal(true);
  readonly processingId = signal<number | null>(null);
  readonly error = signal<string | null>(null);
  readonly historyPage = signal(0);
  readonly historyForm = new FormGroup({
    status: new FormControl<OrderStatus | ''>('', { nonNullable: true }),
    from: new FormControl('', { nonNullable: true }),
    to: new FormControl('', { nonNullable: true }),
    direction: new FormControl<'ASC' | 'DESC'>('DESC', { nonNullable: true }),
  });
  readonly editingMenuId = signal<number | null>(null);
  readonly editingSectionId = signal<number | null>(null);
  readonly editingGroupId = signal<number | null>(null);
  readonly editingAddonId = signal<number | null>(null);
  readonly menuForm = new FormGroup({
    name: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(100)],
    }),
    description: new FormControl('', { nonNullable: true, validators: [Validators.maxLength(500)] }),
    basePrice: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(0), Validators.max(999999)],
    }),
    imageUrl: new FormControl('', { nonNullable: true, validators: [Validators.maxLength(255)] }),
    menuSectionId: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(1)],
    }),
    available: new FormControl(true, { nonNullable: true }),
    addonGroupIds: new FormControl<number[]>([], { nonNullable: true }),
  });
  readonly sectionForm = new FormGroup({
    name: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(100)],
    }),
    description: new FormControl('', {
      nonNullable: true,
      validators: [Validators.maxLength(500)],
    }),
    active: new FormControl(true, { nonNullable: true }),
  });
  readonly groupForm = new FormGroup({
    name: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(100)],
    }),
    minSelections: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(0)],
    }),
    maxSelections: new FormControl(1, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(1), Validators.max(20)],
    }),
  });
  readonly addonForm = new FormGroup({
    groupId: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(1)],
    }),
    name: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(100)],
    }),
    additionalPrice: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(0), Validators.max(999999)],
    }),
    available: new FormControl(true, { nonNullable: true }),
  });
  constructor() {
    this.load();
  }
  load(): void {
    this.loading.set(true);
    const req: Observable<unknown> = (
      this.mode === 'dashboard'
        ? this.api.dashboard()
        : this.mode === 'orders'
          ? this.api.orders()
          : this.mode === 'history'
            ? this.api.history({
                status: this.historyForm.value.status || undefined,
                from: this.historyForm.value.from || undefined,
                to: this.historyForm.value.to || undefined,
                page: this.historyPage(),
                size: 20,
                direction: this.historyForm.value.direction,
              })
            : this.mode === 'menu-items'
              ? forkJoin({ items: this.api.menuItems(), sections: this.api.sections(), groups: this.api.groups() })
              : this.mode === 'sections'
                ? this.api.sections()
                : this.api.groups()
    ) as Observable<unknown>;
    req
      .pipe(
        finalize(() => this.loading.set(false)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (value) => {
          if (this.mode === 'dashboard')
            this.dashboard.set(value as KitchenDashboardSummaryResponse);
          else if (this.mode === 'orders')
            this.activeOrders.set(value as KitchenOrderSummaryResponse[]);
          else if (this.mode === 'history') this.orders.set(value as KitchenOrderPageResponse);
          else if (this.mode === 'menu-items') {
            const context = value as KitchenMenuContext;
            this.menuItems.set(context.items); this.sections.set(context.sections); this.groups.set(context.groups);
          }
          else if (this.mode === 'sections')
            this.sections.set(value as KitchenMenuSectionResponse[]);
          else this.groups.set(value as KitchenAddonGroupResponse[]);
        },
        error: () => this.error.set('Unable to load kitchen data.'),
      });
  }
  availability(item: KitchenMenuItemResponse): void {
    this.processingId.set(item.id);
    this.api
      .availability(item.id, !item.available)
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: (v) => this.menuItems.update((items) => items.map((i) => (i.id === v.id ? v : i))),
        error: () => this.error.set('Availability could not be updated.'),
      });
  }
  applyHistoryFilters(): void {
    this.historyPage.set(0);
    this.load();
  }
  changeHistoryPage(page: number): void {
    this.historyPage.set(page);
    this.load();
  }
  editMenu(item: KitchenMenuItemResponse): void {
    this.editingMenuId.set(item.id);
    this.menuForm.patchValue(item);
  }
  saveMenu(): void {
    if (this.menuForm.invalid) return;
    const raw = this.menuForm.getRawValue();
    const value = { ...raw, description: raw.description || undefined, imageUrl: raw.imageUrl || undefined };
    const request = this.editingMenuId()
      ? this.api.updateMenuItem(this.editingMenuId()!, { name: raw.name, basePrice: raw.basePrice, menuSectionId: raw.menuSectionId, available: raw.available })
      : this.api.createMenuItem(value);
    this.mutate(request, (saved) => {
      this.menuItems.update((rows) =>
        this.editingMenuId()
          ? rows.map((row) => (row.id === saved.id ? saved : row))
          : [...rows, saved],
      );
      this.editingMenuId.set(null);
      this.menuForm.reset({ basePrice: 0, menuSectionId: 0, available: true, addonGroupIds: [] });
    });
  }
  editSection(item: KitchenMenuSectionResponse): void {
    this.editingSectionId.set(item.id);
    this.sectionForm.patchValue(item);
  }
  saveSection(): void {
    if (this.sectionForm.invalid) return;
    const value = this.sectionForm.getRawValue();
    const request = this.editingSectionId()
      ? this.api.updateSection(this.editingSectionId()!, value)
      : this.api.createSection(value);
    this.mutate(request, (saved) => {
      this.sections.update((rows) =>
        this.editingSectionId()
          ? rows.map((row) => (row.id === saved.id ? saved : row))
          : [...rows, saved],
      );
      this.editingSectionId.set(null);
      this.sectionForm.reset({ active: true });
    });
  }
  editGroup(item: KitchenAddonGroupResponse): void {
    this.editingGroupId.set(item.id);
    this.groupForm.patchValue(item);
  }
  saveGroup(): void {
    const value = this.groupForm.getRawValue();
    if (this.groupForm.invalid || value.minSelections > value.maxSelections) {
      this.error.set('Minimum selections cannot exceed maximum selections.');
      return;
    }
    const request = this.editingGroupId()
      ? this.api.updateGroup(this.editingGroupId()!, value)
      : this.api.createGroup(value);
    this.mutate(request, (saved) => {
      this.groups.update((rows) =>
        this.editingGroupId()
          ? rows.map((row) => (row.id === saved.id ? saved : row))
          : [...rows, saved],
      );
      this.editingGroupId.set(null);
      this.groupForm.reset({ minSelections: 0, maxSelections: 1 });
    });
  }
  async deleteGroup(id: number): Promise<void> {
    if (!await this.confirmation.confirm('Delete this add-on group?', 'Delete add-on group', 'Delete')) return;
    this.mutate(this.api.deleteGroup(id), () =>
      this.groups.update((rows) => rows.filter((row) => row.id !== id)),
    );
  }
  editAddon(
    groupId: number,
    item: import('../../models/kitchen.models').KitchenAddonResponse,
  ): void {
    this.editingAddonId.set(item.id);
    this.addonForm.patchValue({ ...item, groupId });
  }
  saveAddon(): void {
    if (this.addonForm.invalid) return;
    const value = this.addonForm.getRawValue();
    const body = {
      name: value.name,
      additionalPrice: value.additionalPrice,
      available: value.available,
    };
    const request = this.editingAddonId()
      ? this.api.updateAddon(this.editingAddonId()!, body)
      : this.api.createAddon(value.groupId, body);
    this.mutate(request, (saved) => {
      this.groups.update((groups) =>
        groups.map((group) =>
          group.id === value.groupId
            ? {
                ...group,
                addons: this.editingAddonId()
                  ? group.addons.map((addon) => (addon.id === saved.id ? saved : addon))
                  : [...group.addons, saved],
              }
            : group,
        ),
      );
      this.editingAddonId.set(null);
      this.addonForm.reset({ groupId: value.groupId, additionalPrice: 0, available: true });
    });
  }
  async deleteAddon(groupId: number, id: number): Promise<void> {
    if (!await this.confirmation.confirm('Delete this add-on?', 'Delete add-on', 'Delete')) return;
    this.mutate(this.api.deleteAddon(id), () =>
      this.groups.update((groups) =>
        groups.map((group) =>
          group.id === groupId
            ? { ...group, addons: group.addons.filter((addon) => addon.id !== id) }
            : group,
        ),
      ),
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
  async removeItem(id: number): Promise<void> {
    if (!await this.confirmation.confirm('Delete this menu item?', 'Delete menu item', 'Delete')) return;
    this.processingId.set(id);
    this.api
      .deleteMenuItem(id)
      .pipe(
        finalize(() => this.processingId.set(null)),
        takeUntilDestroyed(this.destroy),
      )
      .subscribe({
        next: () => this.menuItems.update((v) => v.filter((i) => i.id !== id)),
        error: () => this.error.set('Menu item could not be deleted.'),
      });
  }
}
