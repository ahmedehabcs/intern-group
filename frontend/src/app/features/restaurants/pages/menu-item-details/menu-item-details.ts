import { Component, computed, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed, toSignal } from '@angular/core/rxjs-interop';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { finalize, startWith } from 'rxjs';
import { ImageFallbackDirective } from '../../../../shared/directives/image-fallback.directive';
import { QuantitySelector } from '../../../../shared/components/quantity-selector/quantity-selector';
import { CartItemAddonRequest } from '../../../cart/models/cart.models';
import { CartService } from '../../../cart/services/cart.service';
import { MenuItemDetailsResponse } from '../../models/restaurant.models';
import { RestaurantService } from '../../services/restaurant.service';

@Component({ selector:'app-menu-item-details',imports:[ReactiveFormsModule,RouterLink,ImageFallbackDirective,QuantitySelector],templateUrl:'./menu-item-details.html' })
export class MenuItemDetails {
  private api=inject(RestaurantService);private cart=inject(CartService);private route=inject(ActivatedRoute);private destroy=inject(DestroyRef);
  readonly item=signal<MenuItemDetailsResponse|null>(null);readonly selected=signal<Record<number,number>>({});readonly loading=signal(true);readonly submitting=signal(false);readonly error=signal<string|null>(null);readonly success=signal<string|null>(null);
  readonly form=new FormGroup({quantity:new FormControl(1,{nonNullable:true,validators:[Validators.required,Validators.min(1)]}),specialInstructions:new FormControl('',{nonNullable:true,validators:[Validators.maxLength(500)]})});
  readonly quantity=toSignal(this.form.controls.quantity.valueChanges.pipe(startWith(1)),{initialValue:1});
  readonly total=computed(()=>{const item=this.item();return item?this.quantity()*(item.basePrice+item.addonGroups.flatMap(group=>group.addons).reduce((sum,addon)=>sum+(this.selected()[addon.id]??0)*addon.additionalPrice,0)):0});
  constructor(){this.api.menuItem(Number(this.route.snapshot.paramMap.get('menuItemId'))).pipe(finalize(()=>this.loading.set(false)),takeUntilDestroyed(this.destroy)).subscribe({next:value=>this.item.set(value),error:()=>this.error.set('Unable to load this menu item.')})}
  toggle(id:number,checked:boolean):void{this.selected.update(value=>({...value,[id]:checked?1:0}))}
  submit():void{const item=this.item();if(!item||this.form.invalid)return;for(const group of item.addonGroups){const count=group.addons.filter(addon=>this.selected()[addon.id]).length;if(count<group.minSelections||count>group.maxSelections){this.error.set(`Choose ${group.minSelections}–${group.maxSelections} options for ${group.name}.`);return}}const addons:CartItemAddonRequest[]=Object.entries(this.selected()).filter(([,quantity])=>quantity>0).map(([id,quantity])=>({menuItemAddonId:Number(id),quantity}));this.submitting.set(true);this.error.set(null);this.cart.add({menuItemId:item.id,quantity:this.form.controls.quantity.value,specialInstructions:this.form.controls.specialInstructions.value||undefined,addons}).pipe(finalize(()=>this.submitting.set(false)),takeUntilDestroyed(this.destroy)).subscribe({next:()=>this.success.set('Added to cart.'),error:()=>this.error.set('Could not add this item to your cart.')})}
}
