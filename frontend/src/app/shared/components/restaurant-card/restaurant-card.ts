import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { RestaurantResponse } from '../../../features/restaurants/models/restaurant.models';
import { ImageFallbackDirective } from '../../directives/image-fallback.directive';

@Component({
  selector: 'app-restaurant-card',
  imports: [RouterLink, ImageFallbackDirective],
  template: `<a [routerLink]="['/restaurants', restaurant().id]" class="group block overflow-hidden rounded-xl border border-slate-200 bg-white shadow-sm transition hover:-translate-y-0.5 hover:shadow-md">
    <div class="aspect-[16/9] overflow-hidden bg-orange-50"><img appImageFallback class="h-full w-full object-cover transition duration-300 group-hover:scale-[1.03]" [src]="restaurant().logoUrl || fallback" [alt]="restaurant().name"></div>
    <div class="p-4"><h3 class="truncate text-base font-medium text-slate-900">{{restaurant().name}}</h3><p class="mt-1 line-clamp-2 min-h-10 text-sm leading-5 text-slate-500">{{restaurant().description}}</p><div class="mt-3 flex flex-wrap gap-1.5">@for(category of restaurant().categories.slice(0,3);track category){<span class="rounded-md bg-orange-50 px-2 py-1 text-xs font-medium text-orange-700">{{category}}</span>}</div></div>
  </a>`,
})
export class RestaurantCard { readonly restaurant = input.required<RestaurantResponse>(); readonly fallback='/assets/images/talabaty-food-table.png'; }
