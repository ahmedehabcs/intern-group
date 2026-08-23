import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { TokenService } from '../../core/auth/services/token.service';

@Component({
  selector: 'app-admin-layout',
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './admin-layout.html',
})
export class AdminLayout {
  private readonly tokens = inject(TokenService);
  private readonly router = inject(Router);
  readonly menuOpen = signal(false);

  closeMenu(): void { this.menuOpen.set(false); }
  logout(): void { this.tokens.clear(); void this.router.navigate(['/auth/login']); }
}
