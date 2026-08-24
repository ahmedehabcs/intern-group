import { RestaurantResponse } from '../../restaurants/models/restaurant.models';
export interface MenuItemSearchResponse {
  id: number;
  name: string;
  description: string;
  price: number;
  imageUrl: string;
  restaurantId: number;
  restaurantName: string;
  menuSectionId: number;
  menuSectionName: string;
}
export interface SearchResponse {
  restaurants: RestaurantResponse[];
  menuItems: MenuItemSearchResponse[];
}
