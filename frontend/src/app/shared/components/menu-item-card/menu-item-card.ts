import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuItemResponse } from '../../../features/restaurants/models/restaurant.models';
import { ImageFallbackDirective } from '../../directives/image-fallback.directive';

@Component({
  selector: 'app-menu-item-card',
  imports: [RouterLink, ImageFallbackDirective],
  templateUrl: './menu-item-card.html',
})
export class MenuItemCard {
  readonly item = input.required<MenuItemResponse>();
  readonly fallback = '/assets/images/talabaty-food-table.png';
}
