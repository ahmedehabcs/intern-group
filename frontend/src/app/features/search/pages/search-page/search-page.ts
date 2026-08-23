import { Component, DestroyRef, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { debounceTime, distinctUntilChanged, finalize, map, switchMap, tap } from 'rxjs';
import { RestaurantCard } from '../../../../shared/components/restaurant-card/restaurant-card';
import { SectionHeading } from '../../../../shared/components/section-heading/section-heading';
import { SearchResponse } from '../../models/search.models';
import { SearchService } from '../../services/search.service';

@Component({ selector:'app-search-page',imports:[ReactiveFormsModule,RouterLink,RestaurantCard,SectionHeading],templateUrl:'./search-page.html' })
export class SearchPage {
  private api=inject(SearchService);private route=inject(ActivatedRoute);private router=inject(Router);private destroy=inject(DestroyRef);
  readonly query=new FormControl(this.route.snapshot.queryParamMap.get('search')??'',{nonNullable:true});readonly result=signal<SearchResponse|null>(null);readonly loading=signal(false);readonly error=signal<string|null>(null);
  constructor(){this.query.valueChanges.pipe(map(value=>value.trim()),debounceTime(250),distinctUntilChanged(),tap(query=>void this.router.navigate([], {relativeTo:this.route,queryParams:query?{search:query}:{},replaceUrl:true})),tap(()=>{this.loading.set(true);this.error.set(null)}),switchMap(query=>this.api.search(query).pipe(finalize(()=>this.loading.set(false)))),takeUntilDestroyed(this.destroy)).subscribe({next:value=>this.result.set(value),error:()=>this.error.set('Search is unavailable right now.')});this.query.setValue(this.query.value);}
}
