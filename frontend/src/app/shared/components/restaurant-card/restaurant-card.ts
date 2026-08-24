import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { RestaurantResponse } from '../../../features/restaurants/models/restaurant.models';
import { ImageFallbackDirective } from '../../directives/image-fallback.directive';

@Component({
  selector: 'app-restaurant-card',
  imports: [RouterLink, ImageFallbackDirective],
  templateUrl: './restaurant-card.html',
})
export class RestaurantCard {
  readonly restaurant = input.required<RestaurantResponse>();
  readonly fallback = '/assets/images/talabaty-food-table.png';
}
