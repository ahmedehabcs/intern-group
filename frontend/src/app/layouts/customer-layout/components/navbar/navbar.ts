import { Component, HostListener, inject, OnInit, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { TokenService } from '../../../../core/auth/services/token.service';
import { CartService } from '../../../../features/cart/services/cart.service';
import { AddressResponse } from '../../../../features/account/models/account.models';
import { AddressService } from '../../../../features/account/services/address.service';
import { CartDrawer } from '../../../../shared/components/cart-drawer/cart-drawer';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, ReactiveFormsModule, CartDrawer],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar implements OnInit {
  readonly tokens = inject(TokenService);
  readonly cart = inject(CartService);
  private readonly addressesApi = inject(AddressService);
  private readonly router = inject(Router);
  readonly search = new FormControl('', { nonNullable: true });
  isDark = false;
  readonly defaultAddress = signal<AddressResponse | null>(null);
  readonly profileMenuOpen = signal(false);
  readonly mobileMenuOpen = signal(false);
  readonly cartOpen = signal(false);

  ngOnInit(): void {
    const current = this.router.parseUrl(this.router.url).queryParams['search'];
    if (typeof current === 'string') this.search.setValue(current);
    this.isDark = document.documentElement.dataset['theme'] === 'dark';
    if (this.tokens.isValid() && this.tokens.role() === 'CUSTOMER') {
      this.cart.load().subscribe({ error: () => void 0 });
      this.addressesApi
        .list()
        .subscribe({
          next: (addresses) =>
            this.defaultAddress.set(
              addresses.find((address) => address.isDefault) ?? addresses[0] ?? null,
            ),
          error: () => void 0,
        });
    }
  }

  submitSearch(): void {
    const query = this.search.value.trim();
    void this.router.navigate(['/'], { queryParams: query ? { search: query } : {} });
  }

  focusSearch(): void {
    if (this.router.url.split('?')[0] !== '/') {
      const query = this.search.value.trim();
      void this.router.navigate(['/'], { queryParams: query ? { search: query } : {} });
    }
  }

  toggleTheme(): void {
    this.isDark = !this.isDark;
    const theme = this.isDark ? 'dark' : 'light';
    document.documentElement.dataset['theme'] = theme;
    localStorage.setItem('talabaty-theme', theme);
  }

  logout(): void {
    this.profileMenuOpen.set(false);
    this.tokens.clear();
    this.cart.cart.set(null);
    void this.router.navigate(['/']);
  }

  profileLink(): string {
    switch (this.tokens.role()) {
      case 'CUSTOMER':
        return '/account';
      case 'DRIVER':
        return '/driver/profile';
      case 'ADMIN':
        return '/admin';
      case 'KITCHEN_MANAGER':
        return '/kitchen';
      default:
        return '/auth/login';
    }
  }

  profileLabel(): string {
    switch (this.tokens.role()) {
      case 'CUSTOMER':
        return 'My account';
      case 'DRIVER':
        return 'Driver profile';
      case 'ADMIN':
        return 'Admin dashboard';
      case 'KITCHEN_MANAGER':
        return 'Kitchen workspace';
      default:
        return 'Profile';
    }
  }

  @HostListener('document:click', ['$event'])
  closeMenusOnOutsideClick(event: MouseEvent): void {
    const target = event.target instanceof Element ? event.target : null;
    if (!target?.closest('.profile-menu')) this.profileMenuOpen.set(false);
    if (!target?.closest('.mobile-navigation') && !target?.closest('.menu-button'))
      this.mobileMenuOpen.set(false);
  }
}
