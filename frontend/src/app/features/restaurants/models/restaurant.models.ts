export interface CategoryResponse {
  id: number;
  name: string;
  description: string;
}
export interface RestaurantResponse {
  id: number;
  name: string;
  description: string;
  logoUrl: string;
  categories: string[];
}
export interface MenuItemResponse {
  id: number;
  name: string;
  description: string;
  basePrice: number;
  imageUrl: string;
}
export interface MenuSectionResponse {
  id: number;
  name: string;
  description: string;
  menuItems: MenuItemResponse[];
}
export interface RestaurantDetailsResponse {
  id: number;
  name: string;
  description: string;
  logoUrl: string;
  menuSections: MenuSectionResponse[];
}
export interface MenuItemAddonResponse {
  id: number;
  name: string;
  additionalPrice: number;
}
export interface AddonGroupResponse {
  id: number;
  name: string;
  minSelections: number;
  maxSelections: number;
  addons: MenuItemAddonResponse[];
}
export interface MenuItemDetailsResponse extends MenuItemResponse {
  restaurantId: number;
  menuSectionId: number;
  addonGroups: AddonGroupResponse[];
}
