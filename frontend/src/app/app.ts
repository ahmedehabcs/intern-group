import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { environment } from '../environments/environment';
import { ConfirmationDialog } from './shared/components/confirmation-dialog/confirmation-dialog';
import { ScrollToTopService } from './core/navigation/scroll-to-top.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ConfirmationDialog],
  templateUrl: './app.html',
  host: {
    class: 'block w-full overflow-x-clip',
  },
})
export class App implements OnInit {
  readonly mockEnabled = environment.mock.enabled;
  private readonly scrollToTop = inject(ScrollToTopService);

  ngOnInit(): void {
    const savedTheme = localStorage.getItem('talabaty-theme');
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
    document.documentElement.dataset['theme'] = savedTheme ?? (prefersDark ? 'dark' : 'light');
  }
}
