import { DOCUMENT } from '@angular/common';
import { inject, Injectable } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ScrollToTopService {
  private readonly router = inject(Router);
  private readonly document = inject(DOCUMENT);

  constructor() {
    this.router.events
      .pipe(filter((event): event is NavigationEnd => event instanceof NavigationEnd))
      .subscribe(() => {
        this.document.defaultView?.requestAnimationFrame(() => {
          this.document.defaultView?.scrollTo({ top: 0, left: 0, behavior: 'smooth' });
        });
      });
  }
}
