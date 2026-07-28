import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { RestaurantNavbar } from './components/restaurant-navbar/restaurant-navbar';
import { RestaurantSidebar } from './components/restaurant-sidebar/restaurant-sidebar';

@Component({
  selector: 'app-restaurant-layout',
  imports: [RestaurantNavbar, RestaurantSidebar, RouterOutlet],
  templateUrl: './restaurant-layout.html',
  styleUrl: './restaurant-layout.scss',
})
export class RestaurantLayout {}
