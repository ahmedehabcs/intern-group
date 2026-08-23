import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuItemResponse } from '../../../features/restaurants/models/restaurant.models';
import { ImageFallbackDirective } from '../../directives/image-fallback.directive';

@Component({ selector:'app-menu-item-card',imports:[RouterLink,ImageFallbackDirective],template:`<a [routerLink]="['/menu-items',item().id]" class="group flex h-full overflow-hidden rounded-xl border border-slate-200 bg-white shadow-sm transition hover:shadow-md"><div class="min-w-0 flex-1 p-4"><h3 class="font-medium text-slate-900">{{item().name}}</h3><p class="mt-1 line-clamp-2 text-sm leading-5 text-slate-500">{{item().description}}</p><p class="mt-4 font-medium text-orange-600">EGP {{item().basePrice}}</p></div><img appImageFallback class="aspect-square w-32 object-cover sm:w-36" [src]="item().imageUrl||fallback" [alt]="item().name"></a>` })
export class MenuItemCard { readonly item=input.required<MenuItemResponse>(); readonly fallback='/assets/images/talabaty-food-table.png'; }
