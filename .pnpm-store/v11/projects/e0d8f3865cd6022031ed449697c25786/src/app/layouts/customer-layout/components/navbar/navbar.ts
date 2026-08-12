import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar implements OnInit {
  isDark = false;

  ngOnInit(): void {
    this.isDark = document.documentElement.dataset['theme'] === 'dark';
  }

  toggleTheme(): void {
    this.isDark = !this.isDark;
    document.documentElement.dataset['theme'] = this.isDark ? 'dark' : 'light';
    localStorage.setItem('talabaty-theme', this.isDark ? 'dark' : 'light');
  }
}
