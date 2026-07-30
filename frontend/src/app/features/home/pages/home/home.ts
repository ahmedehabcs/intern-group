import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

interface Category {
  id: string;
  name: string;
  subtitle: string;
  position: string;
}

interface Store {
  id: string;
  category: string;
  name: string;
  cuisine: string;
  rating: number;
  eta: string;
  delivery: string;
  position: string;
}

interface Product {
  id: string;
  store: string;
  name: string;
  description: string;
  price: number;
  position: string;
}

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  selectedCategory = 'food';
  selectedStore = 'artisan';
  query = '';

  readonly categories: Category[] = [
    { id: 'food', name: 'Food', subtitle: '128 places', position: '8% 70%' },
    { id: 'market', name: 'Market', subtitle: '42 stores', position: '4% 28%' },
    { id: 'sweets', name: 'Sweets', subtitle: '31 shops', position: '35% 30%' },
    { id: 'healthy', name: 'Healthy', subtitle: '26 places', position: '69% 28%' },
  ];

  readonly stores: Store[] = [
    { id: 'artisan', category: 'food', name: 'Artisan Pizza Co.', cuisine: 'Italian • Pizza', rating: 4.8, eta: '25–35 min', delivery: 'Free delivery', position: '4% 72%' },
    { id: 'oishii', category: 'food', name: 'Oishii Sushi Bar', cuisine: 'Japanese • Sushi', rating: 4.7, eta: '15–20 min', delivery: 'LE 35', position: '98% 70%' },
    { id: 'oak', category: 'food', name: 'Oak & Ember', cuisine: 'Burgers • Grill', rating: 4.6, eta: '20–30 min', delivery: 'Free delivery', position: '52% 68%' },
    { id: 'fresh', category: 'market', name: 'Fresh Basket', cuisine: 'Groceries • Organic', rating: 4.9, eta: '20–25 min', delivery: 'Free delivery', position: '1% 25%' },
    { id: 'daily', category: 'market', name: 'Daily Market', cuisine: 'Market • Essentials', rating: 4.5, eta: '30–40 min', delivery: 'LE 25', position: '14% 30%' },
    { id: 'crumb', category: 'sweets', name: 'Golden Crumb', cuisine: 'Cakes • Desserts', rating: 4.9, eta: '20–30 min', delivery: 'Free delivery', position: '37% 30%' },
    { id: 'bliss', category: 'sweets', name: 'Sugar Bliss', cuisine: 'Sweets • Bakery', rating: 4.7, eta: '25–35 min', delivery: 'LE 35', position: '40% 31%' },
    { id: 'green', category: 'healthy', name: 'Green Table', cuisine: 'Salads • Bowls', rating: 4.8, eta: '15–25 min', delivery: 'Free delivery', position: '70% 31%' },
    { id: 'balance', category: 'healthy', name: 'Balance Kitchen', cuisine: 'Healthy • Protein', rating: 4.6, eta: '25–35 min', delivery: 'LE 25', position: '69% 34%' },
  ];

  readonly products: Product[] = [
    { id: 'p1', store: 'artisan', name: 'Margherita Classica', description: 'San Marzano tomato, fior di latte, fresh basil', price: 12500, position: '4% 73%' },
    { id: 'p2', store: 'artisan', name: 'Truffle Funghi', description: 'Wild mushrooms, mozzarella, parmesan, truffle oil', price: 16500, position: '13% 71%' },
    { id: 'p3', store: 'artisan', name: 'Spicy Pepperoni', description: 'Beef pepperoni, hot honey, mozzarella', price: 15000, position: '20% 73%' },
    { id: 's1', store: 'oishii', name: 'Oishii Signature Set', description: 'Salmon, tuna, shrimp nigiri and maki selection', price: 22000, position: '96% 72%' },
    { id: 's2', store: 'oishii', name: 'Salmon Lover', description: 'Fresh salmon nigiri, sashimi and avocado roll', price: 18500, position: '92% 69%' },
    { id: 'b1', store: 'oak', name: 'Ember House Burger', description: 'Chargrilled beef, cheddar, pickles, house sauce', price: 13500, position: '52% 67%' },
    { id: 'b2', store: 'oak', name: 'Truffle Smash', description: 'Double beef, caramelized onions and truffle aioli', price: 15500, position: '54% 69%' },
    { id: 'm1', store: 'fresh', name: 'Farm Fresh Box', description: 'A seasonal selection of fruit and vegetables', price: 18000, position: '3% 27%' },
    { id: 'm2', store: 'daily', name: 'Weekly Essentials', description: 'Fresh pantry staples for your kitchen', price: 26000, position: '10% 28%' },
    { id: 'c1', store: 'crumb', name: 'Berry Celebration Cake', description: 'Vanilla cream, mixed berries and almond crumb', price: 28000, position: '36% 31%' },
    { id: 'c2', store: 'bliss', name: 'Mini Cake Box', description: 'Four handcrafted seasonal mini cakes', price: 19500, position: '39% 31%' },
    { id: 'h1', store: 'green', name: 'Garden Power Bowl', description: 'Greens, cucumber, tomato, grains and lemon dressing', price: 11000, position: '68% 30%' },
    { id: 'h2', store: 'balance', name: 'Protein Crunch Salad', description: 'Chicken, greens, seasonal vegetables and seeds', price: 13500, position: '70% 29%' },
  ];

  get visibleStores(): Store[] {
    return this.stores.filter((store) => store.category === this.selectedCategory);
  }

  get selectedStoreData(): Store | undefined {
    return this.stores.find((store) => store.id === this.selectedStore);
  }

  get visibleProducts(): Product[] {
    return this.products.filter((product) => {
      const matchesStore = product.store === this.selectedStore;
      const matchesQuery =
        !this.query ||
        product.name.toLowerCase().includes(this.query.toLowerCase()) ||
        product.description.toLowerCase().includes(this.query.toLowerCase());
      return matchesStore && matchesQuery;
    });
  }

  selectCategory(id: string): void {
    this.selectedCategory = id;
    this.selectedStore = this.stores.find((store) => store.category === id)?.id ?? '';
  }

  selectStore(id: string): void {
    this.selectedStore = id;
    setTimeout(() => document.querySelector('#products')?.scrollIntoView({ behavior: 'smooth', block: 'start' }));
  }
}
