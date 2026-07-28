import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { AdminBreadcrumbs } from './components/admin-breadcrumbs/admin-breadcrumbs';
import { AdminNavbar } from './components/admin-navbar/admin-navbar';
import { AdminSidebar } from './components/admin-sidebar/admin-sidebar';

@Component({
  selector: 'app-admin-layout',
  imports: [AdminNavbar, AdminSidebar, AdminBreadcrumbs, RouterOutlet],
  templateUrl: './admin-layout.html',
  styleUrl: './admin-layout.scss',
})
export class AdminLayout {}
