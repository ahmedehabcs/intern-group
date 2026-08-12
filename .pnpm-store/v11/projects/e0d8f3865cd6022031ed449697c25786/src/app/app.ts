import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  host: {
    class: 'block w-full overflow-x-clip',
  },
})
export class App implements OnInit {
  ngOnInit(): void {
    const saved = localStorage.getItem('talabaty-theme');
    const isDark =
      saved === 'dark' ||
      (!saved && window.matchMedia('(prefers-color-scheme: dark)').matches);
    document.documentElement.dataset['theme'] = isDark ? 'dark' : 'light';
  }
}
