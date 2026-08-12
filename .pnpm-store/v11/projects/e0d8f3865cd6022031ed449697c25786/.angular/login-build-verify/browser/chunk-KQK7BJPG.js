import {
  RouterLink
} from "./chunk-TBWUJ4NH.js";
import {
  CommonModule,
  Component,
  DecimalPipe,
  setClassMetadata,
  ɵsetClassDebugInfo,
  ɵɵadvance,
  ɵɵattribute,
  ɵɵclassProp,
  ɵɵdefineComponent,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵgetCurrentView,
  ɵɵlistener,
  ɵɵnamespaceHTML,
  ɵɵnamespaceSVG,
  ɵɵnextContext,
  ɵɵpipe,
  ɵɵpipeBind1,
  ɵɵreference,
  ɵɵrepeater,
  ɵɵrepeaterCreate,
  ɵɵresetView,
  ɵɵrestoreView,
  ɵɵstyleProp,
  ɵɵtext,
  ɵɵtextInterpolate,
  ɵɵtextInterpolate1
} from "./chunk-XUN6663C.js";
import "./chunk-GOMI4DH3.js";

// src/app/features/home/pages/home/home.ts
var _forTrack0 = ($index, $item) => $item.id;
function Home_For_28_Template(rf, ctx) {
  if (rf & 1) {
    const _r3 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "button", 36);
    \u0275\u0275listener("click", function Home_For_28_Template_button_click_0_listener() {
      const category_r4 = \u0275\u0275restoreView(_r3).$implicit;
      const ctx_r4 = \u0275\u0275nextContext();
      return \u0275\u0275resetView(ctx_r4.selectCategory(category_r4.id));
    });
    \u0275\u0275element(1, "span", 37);
    \u0275\u0275elementStart(2, "span", 38)(3, "strong");
    \u0275\u0275text(4);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(5, "small");
    \u0275\u0275text(6);
    \u0275\u0275elementEnd()();
    \u0275\u0275element(7, "span", 39);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const category_r4 = ctx.$implicit;
    const ctx_r4 = \u0275\u0275nextContext();
    \u0275\u0275classProp("active", category_r4.id === ctx_r4.selectedCategory);
    \u0275\u0275advance();
    \u0275\u0275styleProp("background-position", category_r4.position);
    \u0275\u0275attribute("aria-label", category_r4.name);
    \u0275\u0275advance(3);
    \u0275\u0275textInterpolate(category_r4.name);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(category_r4.subtitle);
  }
}
function Home_For_40_Template(rf, ctx) {
  if (rf & 1) {
    const _r6 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "button", 40);
    \u0275\u0275listener("click", function Home_For_40_Template_button_click_0_listener() {
      const store_r7 = \u0275\u0275restoreView(_r6).$implicit;
      const ctx_r4 = \u0275\u0275nextContext();
      return \u0275\u0275resetView(ctx_r4.selectStore(store_r7.id));
    });
    \u0275\u0275elementStart(1, "span", 41)(2, "span", 42);
    \u0275\u0275text(3);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(4, "span", 43);
    \u0275\u0275text(5);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(6, "span", 44)(7, "span")(8, "strong");
    \u0275\u0275text(9);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(10, "small");
    \u0275\u0275text(11);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(12, "span", 45);
    \u0275\u0275text(13);
    \u0275\u0275elementEnd()()();
  }
  if (rf & 2) {
    const store_r7 = ctx.$implicit;
    const ctx_r4 = \u0275\u0275nextContext();
    \u0275\u0275classProp("active", store_r7.id === ctx_r4.selectedStore);
    \u0275\u0275advance();
    \u0275\u0275styleProp("background-position", store_r7.position);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate1("\u2605 ", store_r7.rating);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(store_r7.eta);
    \u0275\u0275advance(4);
    \u0275\u0275textInterpolate(store_r7.name);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(store_r7.cuisine);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(store_r7.delivery);
  }
}
function Home_For_52_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "article", 22);
    \u0275\u0275element(1, "div", 46);
    \u0275\u0275elementStart(2, "div", 47)(3, "div")(4, "h3");
    \u0275\u0275text(5);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(6, "p");
    \u0275\u0275text(7);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(8, "div", 48)(9, "strong");
    \u0275\u0275text(10);
    \u0275\u0275pipe(11, "number");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(12, "button", 49);
    \u0275\u0275text(13, "+");
    \u0275\u0275elementEnd()()()();
  }
  if (rf & 2) {
    const product_r8 = ctx.$implicit;
    \u0275\u0275advance();
    \u0275\u0275styleProp("background-position", product_r8.position);
    \u0275\u0275advance(4);
    \u0275\u0275textInterpolate(product_r8.name);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(product_r8.description);
    \u0275\u0275advance(3);
    \u0275\u0275textInterpolate1("LE ", \u0275\u0275pipeBind1(11, 5, product_r8.price));
  }
}
function Home_ForEmpty_53_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "div", 23);
    \u0275\u0275text(1, "No dishes matched your search.");
    \u0275\u0275elementEnd();
  }
}
var Home = class _Home {
  selectedCategory = "food";
  selectedStore = "artisan";
  query = "";
  categories = [
    { id: "food", name: "Food", subtitle: "128 places", position: "8% 70%" },
    { id: "market", name: "Market", subtitle: "42 stores", position: "4% 28%" },
    { id: "sweets", name: "Sweets", subtitle: "31 shops", position: "35% 30%" },
    { id: "healthy", name: "Healthy", subtitle: "26 places", position: "69% 28%" }
  ];
  stores = [
    { id: "artisan", category: "food", name: "Artisan Pizza Co.", cuisine: "Italian \u2022 Pizza", rating: 4.8, eta: "25\u201335 min", delivery: "Free delivery", position: "4% 72%" },
    { id: "oishii", category: "food", name: "Oishii Sushi Bar", cuisine: "Japanese \u2022 Sushi", rating: 4.7, eta: "15\u201320 min", delivery: "LE 35", position: "98% 70%" },
    { id: "oak", category: "food", name: "Oak & Ember", cuisine: "Burgers \u2022 Grill", rating: 4.6, eta: "20\u201330 min", delivery: "Free delivery", position: "52% 68%" },
    { id: "fresh", category: "market", name: "Fresh Basket", cuisine: "Groceries \u2022 Organic", rating: 4.9, eta: "20\u201325 min", delivery: "Free delivery", position: "1% 25%" },
    { id: "daily", category: "market", name: "Daily Market", cuisine: "Market \u2022 Essentials", rating: 4.5, eta: "30\u201340 min", delivery: "LE 25", position: "14% 30%" },
    { id: "crumb", category: "sweets", name: "Golden Crumb", cuisine: "Cakes \u2022 Desserts", rating: 4.9, eta: "20\u201330 min", delivery: "Free delivery", position: "37% 30%" },
    { id: "bliss", category: "sweets", name: "Sugar Bliss", cuisine: "Sweets \u2022 Bakery", rating: 4.7, eta: "25\u201335 min", delivery: "LE 35", position: "40% 31%" },
    { id: "green", category: "healthy", name: "Green Table", cuisine: "Salads \u2022 Bowls", rating: 4.8, eta: "15\u201325 min", delivery: "Free delivery", position: "70% 31%" },
    { id: "balance", category: "healthy", name: "Balance Kitchen", cuisine: "Healthy \u2022 Protein", rating: 4.6, eta: "25\u201335 min", delivery: "LE 25", position: "69% 34%" }
  ];
  products = [
    { id: "p1", store: "artisan", name: "Margherita Classica", description: "San Marzano tomato, fior di latte, fresh basil", price: 12500, position: "4% 73%" },
    { id: "p2", store: "artisan", name: "Truffle Funghi", description: "Wild mushrooms, mozzarella, parmesan, truffle oil", price: 16500, position: "13% 71%" },
    { id: "p3", store: "artisan", name: "Spicy Pepperoni", description: "Beef pepperoni, hot honey, mozzarella", price: 15e3, position: "20% 73%" },
    { id: "s1", store: "oishii", name: "Oishii Signature Set", description: "Salmon, tuna, shrimp nigiri and maki selection", price: 22e3, position: "96% 72%" },
    { id: "s2", store: "oishii", name: "Salmon Lover", description: "Fresh salmon nigiri, sashimi and avocado roll", price: 18500, position: "92% 69%" },
    { id: "b1", store: "oak", name: "Ember House Burger", description: "Chargrilled beef, cheddar, pickles, house sauce", price: 13500, position: "52% 67%" },
    { id: "b2", store: "oak", name: "Truffle Smash", description: "Double beef, caramelized onions and truffle aioli", price: 15500, position: "54% 69%" },
    { id: "m1", store: "fresh", name: "Farm Fresh Box", description: "A seasonal selection of fruit and vegetables", price: 18e3, position: "3% 27%" },
    { id: "m2", store: "daily", name: "Weekly Essentials", description: "Fresh pantry staples for your kitchen", price: 26e3, position: "10% 28%" },
    { id: "c1", store: "crumb", name: "Berry Celebration Cake", description: "Vanilla cream, mixed berries and almond crumb", price: 28e3, position: "36% 31%" },
    { id: "c2", store: "bliss", name: "Mini Cake Box", description: "Four handcrafted seasonal mini cakes", price: 19500, position: "39% 31%" },
    { id: "h1", store: "green", name: "Garden Power Bowl", description: "Greens, cucumber, tomato, grains and lemon dressing", price: 11e3, position: "68% 30%" },
    { id: "h2", store: "balance", name: "Protein Crunch Salad", description: "Chicken, greens, seasonal vegetables and seeds", price: 13500, position: "70% 29%" }
  ];
  get visibleStores() {
    return this.stores.filter((store) => store.category === this.selectedCategory);
  }
  get selectedStoreData() {
    return this.stores.find((store) => store.id === this.selectedStore);
  }
  get visibleProducts() {
    return this.products.filter((product) => {
      const matchesStore = product.store === this.selectedStore;
      const matchesQuery = !this.query || product.name.toLowerCase().includes(this.query.toLowerCase()) || product.description.toLowerCase().includes(this.query.toLowerCase());
      return matchesStore && matchesQuery;
    });
  }
  selectCategory(id) {
    this.selectedCategory = id;
    this.selectedStore = this.stores.find((store) => store.category === id)?.id ?? "";
  }
  selectStore(id) {
    this.selectedStore = id;
    setTimeout(() => document.querySelector("#products")?.scrollIntoView({ behavior: "smooth", block: "start" }));
  }
  static \u0275fac = function Home_Factory(__ngFactoryType__) {
    return new (__ngFactoryType__ || _Home)();
  };
  static \u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _Home, selectors: [["app-home"]], decls: 79, vars: 4, consts: [["searchInput", ""], [1, "home-page"], [1, "mobile-location"], ["viewBox", "0 0 24 24", "aria-hidden", "true"], ["d", "M20 10c0 5-8 12-8 12S4 15 4 10a8 8 0 1 1 16 0Z"], ["cx", "12", "cy", "10", "r", "2.5"], [1, "mobile-search"], ["cx", "11", "cy", "11", "r", "7"], ["d", "m20 20-4-4"], ["type", "search", "placeholder", "Search dishes", 3, "input"], ["d", "M4 7h10M18 7h2M4 17h2m4 0h10M8 4v6m8 4v6"], [1, "browse-section", "categories-section"], [1, "section-heading"], [1, "step-label"], [1, "section-count"], [1, "category-grid"], ["type", "button", 1, "category-card", 3, "active"], [1, "browse-section"], [1, "store-grid"], ["type", "button", 1, "store-card", 3, "active"], ["id", "products", 1, "browse-section", "products-section"], [1, "product-grid"], [1, "product-card"], [1, "empty-state"], ["aria-label", "Mobile navigation", 1, "mobile-nav"], ["routerLink", "/", 1, "active"], ["viewBox", "0 0 24 24"], ["d", "m3 11 9-8 9 8v9h-6v-6H9v6H3Z"], ["routerLink", "/search"], ["type", "button"], ["d", "M3 4h2l2.3 10.1a2 2 0 0 0 2 1.5h7.9a2 2 0 0 0 2-1.6L21 7H6"], ["cx", "10", "cy", "20", "r", "1"], ["cx", "18", "cy", "20", "r", "1"], ["routerLink", "/auth/login"], ["cx", "12", "cy", "8", "r", "4"], ["d", "M4 21a8 8 0 0 1 16 0"], ["type", "button", 1, "category-card", 3, "click"], ["role", "img", 1, "card-photo"], [1, "category-copy"], ["aria-hidden", "true", 1, "selected-dot"], ["type", "button", 1, "store-card", 3, "click"], [1, "store-photo"], [1, "rating"], [1, "eta"], [1, "store-info"], [1, "delivery"], [1, "product-photo"], [1, "product-info"], [1, "product-bottom"], ["type", "button", "aria-label", "Add to cart"]], template: function Home_Template(rf, ctx) {
    if (rf & 1) {
      const _r1 = \u0275\u0275getCurrentView();
      \u0275\u0275elementStart(0, "div", 1)(1, "div", 2);
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(2, "svg", 3);
      \u0275\u0275element(3, "path", 4)(4, "circle", 5);
      \u0275\u0275elementEnd();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(5, "span");
      \u0275\u0275text(6, "Delivering to: ");
      \u0275\u0275elementStart(7, "strong");
      \u0275\u0275text(8, "Al-Mansour, Baghdad");
      \u0275\u0275elementEnd()()();
      \u0275\u0275elementStart(9, "label", 6);
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(10, "svg", 3);
      \u0275\u0275element(11, "circle", 7)(12, "path", 8);
      \u0275\u0275elementEnd();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(13, "input", 9, 0);
      \u0275\u0275listener("input", function Home_Template_input_input_13_listener() {
        \u0275\u0275restoreView(_r1);
        const searchInput_r2 = \u0275\u0275reference(14);
        return \u0275\u0275resetView(ctx.query = searchInput_r2.value);
      });
      \u0275\u0275elementEnd();
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(15, "svg", 3);
      \u0275\u0275element(16, "path", 10);
      \u0275\u0275elementEnd()();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(17, "section", 11)(18, "div", 12)(19, "div")(20, "span", 13);
      \u0275\u0275text(21, "01 \u2014 Choose");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(22, "h1");
      \u0275\u0275text(23, "Category");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(24, "span", 14);
      \u0275\u0275text(25);
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(26, "div", 15);
      \u0275\u0275repeaterCreate(27, Home_For_28_Template, 8, 7, "button", 16, _forTrack0);
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(29, "section", 17)(30, "div", 12)(31, "div")(32, "span", 13);
      \u0275\u0275text(33, "02 \u2014 Select");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(34, "h2");
      \u0275\u0275text(35, "Stores & brands");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(36, "span", 14);
      \u0275\u0275text(37, "Popular near you");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(38, "div", 18);
      \u0275\u0275repeaterCreate(39, Home_For_40_Template, 14, 9, "button", 19, _forTrack0);
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(41, "section", 20)(42, "div", 12)(43, "div")(44, "span", 13);
      \u0275\u0275text(45, "03 \u2014 Order");
      \u0275\u0275elementEnd();
      \u0275\u0275elementStart(46, "h2");
      \u0275\u0275text(47);
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(48, "span", 14);
      \u0275\u0275text(49);
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(50, "div", 21);
      \u0275\u0275repeaterCreate(51, Home_For_52_Template, 14, 7, "article", 22, _forTrack0, false, Home_ForEmpty_53_Template, 2, 0, "div", 23);
      \u0275\u0275elementEnd()()();
      \u0275\u0275elementStart(54, "nav", 24)(55, "a", 25);
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(56, "svg", 26);
      \u0275\u0275element(57, "path", 27);
      \u0275\u0275elementEnd();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(58, "span");
      \u0275\u0275text(59, "Home");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(60, "a", 28);
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(61, "svg", 26);
      \u0275\u0275element(62, "circle", 7)(63, "path", 8);
      \u0275\u0275elementEnd();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(64, "span");
      \u0275\u0275text(65, "Search");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(66, "button", 29);
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(67, "svg", 26);
      \u0275\u0275element(68, "path", 30)(69, "circle", 31)(70, "circle", 32);
      \u0275\u0275elementEnd();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(71, "span");
      \u0275\u0275text(72, "Cart");
      \u0275\u0275elementEnd()();
      \u0275\u0275elementStart(73, "a", 33);
      \u0275\u0275namespaceSVG();
      \u0275\u0275elementStart(74, "svg", 26);
      \u0275\u0275element(75, "circle", 34)(76, "path", 35);
      \u0275\u0275elementEnd();
      \u0275\u0275namespaceHTML();
      \u0275\u0275elementStart(77, "span");
      \u0275\u0275text(78, "Profile");
      \u0275\u0275elementEnd()()();
    }
    if (rf & 2) {
      \u0275\u0275advance(25);
      \u0275\u0275textInterpolate1("", ctx.categories.length, " categories");
      \u0275\u0275advance(2);
      \u0275\u0275repeater(ctx.categories);
      \u0275\u0275advance(12);
      \u0275\u0275repeater(ctx.visibleStores);
      \u0275\u0275advance(8);
      \u0275\u0275textInterpolate1("", ctx.selectedStoreData?.name, " menu");
      \u0275\u0275advance(2);
      \u0275\u0275textInterpolate1("", ctx.visibleProducts.length, " dishes");
      \u0275\u0275advance(2);
      \u0275\u0275repeater(ctx.visibleProducts);
    }
  }, dependencies: [CommonModule, RouterLink, DecimalPipe], styles: ['\n.home-page[_ngcontent-%COMP%] {\n  min-height: calc(100vh - 78px);\n  padding: 34px max(32px, calc((100vw - 1240px) / 2)) 80px;\n  background: var(--%NS%home-bg);\n}\n.mobile-location[_ngcontent-%COMP%], \n.mobile-search[_ngcontent-%COMP%], \n.mobile-nav[_ngcontent-%COMP%] {\n  display: none;\n}\n.browse-section[_ngcontent-%COMP%] {\n  margin-top: 50px;\n  scroll-margin-top: 96px;\n}\n.browse-section[_ngcontent-%COMP%]:first-of-type {\n  margin-top: 0;\n}\n.section-heading[_ngcontent-%COMP%] {\n  margin-bottom: 20px;\n  display: flex;\n  align-items: end;\n  justify-content: space-between;\n  gap: 20px;\n}\n.section-heading[_ngcontent-%COMP%]   h1[_ngcontent-%COMP%], \n.section-heading[_ngcontent-%COMP%]   h2[_ngcontent-%COMP%] {\n  margin: 4px 0 0;\n  color: var(--%NS%text);\n  font-family: "Playfair Display", serif;\n  font-size: clamp(25px, 3vw, 36px);\n}\n.step-label[_ngcontent-%COMP%] {\n  color: var(--%NS%brand);\n  font-size: 11px;\n  font-weight: 700;\n  letter-spacing: 0.18em;\n  text-transform: uppercase;\n}\n.section-count[_ngcontent-%COMP%] {\n  color: var(--%NS%text-soft);\n  font-size: 13px;\n}\n.category-grid[_ngcontent-%COMP%] {\n  display: grid;\n  grid-template-columns: repeat(4, 1fr);\n  gap: 18px;\n}\n.category-card[_ngcontent-%COMP%], \n.store-card[_ngcontent-%COMP%] {\n  padding: 0;\n  overflow: hidden;\n  border: 1px solid var(--%NS%border);\n  background: var(--%NS%surface);\n  color: var(--%NS%text);\n  text-align: left;\n  cursor: pointer;\n  box-shadow: var(--%NS%shadow-sm);\n  transition:\n    transform 180ms ease,\n    border 180ms ease,\n    box-shadow 180ms ease;\n}\n.category-card[_ngcontent-%COMP%] {\n  position: relative;\n  aspect-ratio: 1;\n  border-radius: 18px;\n}\n.category-card[_ngcontent-%COMP%]:hover, \n.store-card[_ngcontent-%COMP%]:hover {\n  transform: translateY(-3px);\n}\n.category-card.active[_ngcontent-%COMP%], \n.store-card.active[_ngcontent-%COMP%] {\n  border-color: var(--%NS%brand);\n  box-shadow: 0 0 0 2px color-mix(in srgb, var(--%NS%brand) 16%, transparent), var(--%NS%shadow-sm);\n}\n.card-photo[_ngcontent-%COMP%] {\n  position: absolute;\n  inset: 0;\n  display: block;\n  background-image:\n    linear-gradient(\n      180deg,\n      transparent 25%,\n      rgba(15, 9, 4, 0.78) 100%),\n    url(/assets/images/talabaty-food-table.png);\n  background-size: auto 260%;\n}\n.category-copy[_ngcontent-%COMP%] {\n  position: absolute;\n  z-index: 1;\n  left: 18px;\n  bottom: 16px;\n  display: grid;\n  color: #fff;\n}\n.category-copy[_ngcontent-%COMP%]   strong[_ngcontent-%COMP%] {\n  font-size: 20px;\n}\n.category-copy[_ngcontent-%COMP%]   small[_ngcontent-%COMP%] {\n  margin-top: 2px;\n  color: rgba(255, 255, 255, 0.74);\n}\n.selected-dot[_ngcontent-%COMP%] {\n  position: absolute;\n  z-index: 2;\n  top: 14px;\n  right: 14px;\n  width: 19px;\n  height: 19px;\n  border: 5px solid rgba(255, 255, 255, 0.85);\n  border-radius: 50%;\n  background: transparent;\n}\n.category-card.active[_ngcontent-%COMP%]   .selected-dot[_ngcontent-%COMP%] {\n  background: var(--%NS%brand-strong);\n}\n.store-grid[_ngcontent-%COMP%] {\n  display: grid;\n  grid-template-columns: repeat(3, 1fr);\n  gap: 20px;\n}\n.store-card[_ngcontent-%COMP%] {\n  border-radius: 20px;\n}\n.store-photo[_ngcontent-%COMP%] {\n  position: relative;\n  height: 190px;\n  display: block;\n  background-image: url(/assets/images/talabaty-food-table.png);\n  background-size: auto 210%;\n}\n.rating[_ngcontent-%COMP%], \n.eta[_ngcontent-%COMP%] {\n  position: absolute;\n  top: 13px;\n  padding: 6px 9px;\n  border-radius: 9px;\n  background: rgba(255, 255, 255, 0.92);\n  color: #2b251f;\n  font-size: 12px;\n  font-weight: 700;\n  -webkit-backdrop-filter: blur(8px);\n  backdrop-filter: blur(8px);\n}\n.rating[_ngcontent-%COMP%] {\n  left: 13px;\n}\n.eta[_ngcontent-%COMP%] {\n  right: 13px;\n  background: rgba(26, 22, 18, 0.8);\n  color: #fff;\n}\n.store-info[_ngcontent-%COMP%] {\n  min-height: 88px;\n  display: flex;\n  align-items: center;\n  justify-content: space-between;\n  gap: 12px;\n  padding: 16px;\n}\n.store-info[_ngcontent-%COMP%]    > span[_ngcontent-%COMP%]:first-child {\n  display: grid;\n}\n.store-info[_ngcontent-%COMP%]   strong[_ngcontent-%COMP%] {\n  font-size: 16px;\n}\n.store-info[_ngcontent-%COMP%]   small[_ngcontent-%COMP%] {\n  margin-top: 5px;\n  color: var(--%NS%text-soft);\n}\n.delivery[_ngcontent-%COMP%] {\n  flex: 0 0 auto;\n  padding: 7px 9px;\n  border-radius: 9px;\n  background: var(--%NS%brand-soft);\n  color: var(--%NS%brand);\n  font-size: 10px;\n  font-weight: 700;\n}\n.product-grid[_ngcontent-%COMP%] {\n  display: grid;\n  grid-template-columns: repeat(3, 1fr);\n  gap: 20px;\n}\n.product-card[_ngcontent-%COMP%] {\n  overflow: hidden;\n  border: 1px solid var(--%NS%border);\n  border-radius: 20px;\n  background: var(--%NS%surface);\n  box-shadow: var(--%NS%shadow-sm);\n}\n.product-photo[_ngcontent-%COMP%] {\n  position: relative;\n  height: 180px;\n  background-image: url(/assets/images/talabaty-food-table.png);\n  background-size: auto 230%;\n}\n.product-info[_ngcontent-%COMP%] {\n  min-height: 165px;\n  padding: 18px;\n  display: flex;\n  flex-direction: column;\n  justify-content: space-between;\n}\n.product-info[_ngcontent-%COMP%]   h3[_ngcontent-%COMP%] {\n  margin: 0;\n  font-size: 17px;\n}\n.product-info[_ngcontent-%COMP%]   p[_ngcontent-%COMP%] {\n  min-height: 42px;\n  margin: 7px 0 0;\n  color: var(--%NS%text-soft);\n  font-size: 13px;\n  line-height: 1.55;\n}\n.product-bottom[_ngcontent-%COMP%] {\n  margin-top: 18px;\n  display: flex;\n  align-items: center;\n  justify-content: space-between;\n  color: var(--%NS%brand);\n}\n.product-bottom[_ngcontent-%COMP%]   button[_ngcontent-%COMP%] {\n  width: 38px;\n  height: 38px;\n  border: 0;\n  border-radius: 12px;\n  background: var(--%NS%brand);\n  color: #fff;\n  font-size: 24px;\n  line-height: 1;\n  cursor: pointer;\n}\n.empty-state[_ngcontent-%COMP%] {\n  grid-column: 1 / -1;\n  padding: 44px;\n  border: 1px dashed var(--%NS%border);\n  border-radius: 18px;\n  color: var(--%NS%text-soft);\n  text-align: center;\n}\n@media (max-width: 900px) {\n  .category-grid[_ngcontent-%COMP%] {\n    grid-template-columns: repeat(2, 1fr);\n  }\n  .store-grid[_ngcontent-%COMP%], \n   .product-grid[_ngcontent-%COMP%] {\n    grid-template-columns: repeat(2, 1fr);\n  }\n}\n@media (max-width: 600px) {\n  .home-page[_ngcontent-%COMP%] {\n    width: 100%;\n    min-height: 100vh;\n    padding: 0 18px 105px;\n    overflow-x: hidden;\n  }\n  .mobile-location[_ngcontent-%COMP%] {\n    display: flex;\n    align-items: center;\n    gap: 8px;\n    padding: 8px 0 13px;\n    color: var(--%NS%brand);\n    font-size: 13px;\n  }\n  .mobile-location[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%], \n   .mobile-search[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%], \n   .mobile-nav[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%] {\n    width: 20px;\n    height: 20px;\n    fill: none;\n    stroke: currentColor;\n    stroke-linecap: round;\n    stroke-linejoin: round;\n    stroke-width: 1.8;\n  }\n  .mobile-location[_ngcontent-%COMP%]   span[_ngcontent-%COMP%] {\n    color: var(--%NS%text-soft);\n  }\n  .mobile-location[_ngcontent-%COMP%]   strong[_ngcontent-%COMP%] {\n    color: var(--%NS%brand);\n  }\n  .mobile-search[_ngcontent-%COMP%] {\n    height: 50px;\n    display: flex;\n    align-items: center;\n    gap: 10px;\n    padding: 0 14px;\n    border-radius: 14px;\n    background: var(--%NS%surface-3);\n    color: var(--%NS%text-soft);\n  }\n  .mobile-search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%] {\n    min-width: 0;\n    flex: 1;\n    border: 0;\n    outline: 0;\n    background: transparent;\n    color: var(--%NS%text);\n  }\n  .browse-section[_ngcontent-%COMP%] {\n    margin-top: 40px;\n  }\n  .categories-section[_ngcontent-%COMP%] {\n    margin-top: 32px !important;\n  }\n  .section-heading[_ngcontent-%COMP%] {\n    min-width: 0;\n    align-items: center;\n    margin-bottom: 16px;\n  }\n  .section-heading[_ngcontent-%COMP%]   h1[_ngcontent-%COMP%], \n   .section-heading[_ngcontent-%COMP%]   h2[_ngcontent-%COMP%] {\n    font-family: "DM Sans", sans-serif;\n    font-size: 21px;\n    letter-spacing: -0.02em;\n  }\n  .section-count[_ngcontent-%COMP%] {\n    max-width: 42%;\n    overflow: hidden;\n    font-size: 11px;\n    text-align: right;\n    text-overflow: ellipsis;\n    white-space: nowrap;\n  }\n  .category-grid[_ngcontent-%COMP%] {\n    display: grid;\n    grid-template-columns: repeat(4, minmax(0, 1fr));\n    gap: 10px;\n    overflow: visible;\n    padding: 2px;\n  }\n  .category-card[_ngcontent-%COMP%] {\n    width: 100%;\n    height: auto;\n    min-width: 0;\n    aspect-ratio: 1;\n    border-radius: 14px;\n  }\n  .category-copy[_ngcontent-%COMP%] {\n    right: 5px;\n    bottom: 17%;\n    left: 5px;\n    text-align: center;\n  }\n  .category-copy[_ngcontent-%COMP%]   strong[_ngcontent-%COMP%] {\n    font-size: clamp(11px, 3.3vw, 15px);\n  }\n  .category-copy[_ngcontent-%COMP%]   small[_ngcontent-%COMP%] {\n    display: none;\n  }\n  .selected-dot[_ngcontent-%COMP%] {\n    top: 8%;\n    right: 12%;\n    width: 16px;\n    height: 16px;\n    border-width: 4px;\n  }\n  .store-grid[_ngcontent-%COMP%] {\n    display: grid;\n    grid-template-columns: 1fr;\n    gap: 14px;\n    overflow: visible;\n    padding: 2px;\n  }\n  .store-card[_ngcontent-%COMP%] {\n    width: 100%;\n    min-width: 0;\n  }\n  .store-photo[_ngcontent-%COMP%] {\n    height: 160px;\n  }\n  .store-info[_ngcontent-%COMP%] {\n    min-height: 82px;\n  }\n  .product-grid[_ngcontent-%COMP%] {\n    grid-template-columns: 1fr;\n    gap: 16px;\n  }\n  .product-card[_ngcontent-%COMP%] {\n    display: grid;\n    grid-template-columns: 132px 1fr;\n    min-height: 154px;\n  }\n  .product-photo[_ngcontent-%COMP%] {\n    height: 100%;\n    min-height: 154px;\n  }\n  .product-info[_ngcontent-%COMP%] {\n    min-height: 154px;\n    padding: 14px;\n  }\n  .product-info[_ngcontent-%COMP%]   h3[_ngcontent-%COMP%] {\n    font-size: 15px;\n  }\n  .product-info[_ngcontent-%COMP%]   p[_ngcontent-%COMP%] {\n    min-height: auto;\n    font-size: 11px;\n  }\n  .product-bottom[_ngcontent-%COMP%] {\n    margin-top: 10px;\n    font-size: 13px;\n  }\n  .product-bottom[_ngcontent-%COMP%]   button[_ngcontent-%COMP%] {\n    width: 33px;\n    height: 33px;\n    border-radius: 10px;\n  }\n  .mobile-nav[_ngcontent-%COMP%] {\n    position: fixed;\n    z-index: 40;\n    right: 0;\n    bottom: 0;\n    left: 0;\n    height: 72px;\n    display: grid;\n    grid-template-columns: repeat(4, 1fr);\n    border-top: 1px solid var(--%NS%border);\n    background: color-mix(in srgb, var(--%NS%home-bg) 94%, transparent);\n    box-shadow: 0 -10px 30px rgba(56, 37, 22, 0.08);\n    -webkit-backdrop-filter: blur(14px);\n    backdrop-filter: blur(14px);\n  }\n  .mobile-nav[_ngcontent-%COMP%]   a[_ngcontent-%COMP%], \n   .mobile-nav[_ngcontent-%COMP%]   button[_ngcontent-%COMP%] {\n    display: flex;\n    flex-direction: column;\n    align-items: center;\n    justify-content: center;\n    gap: 4px;\n    border: 0;\n    background: transparent;\n    color: var(--%NS%text-soft);\n    font-size: 11px;\n  }\n  .mobile-nav[_ngcontent-%COMP%]   .active[_ngcontent-%COMP%] {\n    color: var(--%NS%brand);\n  }\n}\n/*# sourceMappingURL=home.css.map */'] });
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && setClassMetadata(Home, [{
    type: Component,
    args: [{ selector: "app-home", imports: [CommonModule, RouterLink], template: '<div class="home-page">\r\n  <div class="mobile-location">\r\n    <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M20 10c0 5-8 12-8 12S4 15 4 10a8 8 0 1 1 16 0Z"></path><circle cx="12" cy="10" r="2.5"></circle></svg>\r\n    <span>Delivering to: <strong>Al-Mansour, Baghdad</strong></span>\r\n  </div>\r\n\r\n  <label class="mobile-search">\r\n    <svg viewBox="0 0 24 24" aria-hidden="true"><circle cx="11" cy="11" r="7"></circle><path d="m20 20-4-4"></path></svg>\r\n    <input #searchInput type="search" placeholder="Search dishes" (input)="query = searchInput.value" />\r\n    <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M4 7h10M18 7h2M4 17h2m4 0h10M8 4v6m8 4v6"></path></svg>\r\n  </label>\r\n\r\n  <section class="browse-section categories-section">\r\n    <div class="section-heading">\r\n      <div>\r\n        <span class="step-label">01 \u2014 Choose</span>\r\n        <h1>Category</h1>\r\n      </div>\r\n      <span class="section-count">{{ categories.length }} categories</span>\r\n    </div>\r\n\r\n    <div class="category-grid">\r\n      @for (category of categories; track category.id) {\r\n        <button\r\n          class="category-card"\r\n          type="button"\r\n          [class.active]="category.id === selectedCategory"\r\n          (click)="selectCategory(category.id)"\r\n        >\r\n          <span\r\n            class="card-photo"\r\n            [style.background-position]="category.position"\r\n            role="img"\r\n            [attr.aria-label]="category.name"\r\n          ></span>\r\n          <span class="category-copy">\r\n            <strong>{{ category.name }}</strong>\r\n            <small>{{ category.subtitle }}</small>\r\n          </span>\r\n          <span class="selected-dot" aria-hidden="true"></span>\r\n        </button>\r\n      }\r\n    </div>\r\n  </section>\r\n\r\n  <section class="browse-section">\r\n    <div class="section-heading">\r\n      <div>\r\n        <span class="step-label">02 \u2014 Select</span>\r\n        <h2>Stores & brands</h2>\r\n      </div>\r\n      <span class="section-count">Popular near you</span>\r\n    </div>\r\n\r\n    <div class="store-grid">\r\n      @for (store of visibleStores; track store.id) {\r\n        <button\r\n          class="store-card"\r\n          type="button"\r\n          [class.active]="store.id === selectedStore"\r\n          (click)="selectStore(store.id)"\r\n        >\r\n          <span class="store-photo" [style.background-position]="store.position">\r\n            <span class="rating">\u2605 {{ store.rating }}</span>\r\n            <span class="eta">{{ store.eta }}</span>\r\n          </span>\r\n          <span class="store-info">\r\n            <span>\r\n              <strong>{{ store.name }}</strong>\r\n              <small>{{ store.cuisine }}</small>\r\n            </span>\r\n            <span class="delivery">{{ store.delivery }}</span>\r\n          </span>\r\n        </button>\r\n      }\r\n    </div>\r\n  </section>\r\n\r\n  <section class="browse-section products-section" id="products">\r\n    <div class="section-heading">\r\n      <div>\r\n        <span class="step-label">03 \u2014 Order</span>\r\n        <h2>{{ selectedStoreData?.name }} menu</h2>\r\n      </div>\r\n      <span class="section-count">{{ visibleProducts.length }} dishes</span>\r\n    </div>\r\n\r\n    <div class="product-grid">\r\n      @for (product of visibleProducts; track product.id) {\r\n        <article class="product-card">\r\n          <div class="product-photo" [style.background-position]="product.position"></div>\r\n          <div class="product-info">\r\n            <div>\r\n              <h3>{{ product.name }}</h3>\r\n              <p>{{ product.description }}</p>\r\n            </div>\r\n            <div class="product-bottom">\r\n              <strong>LE {{ product.price | number }}</strong>\r\n              <button type="button" aria-label="Add to cart">+</button>\r\n            </div>\r\n          </div>\r\n        </article>\r\n      } @empty {\r\n        <div class="empty-state">No dishes matched your search.</div>\r\n      }\r\n    </div>\r\n  </section>\r\n</div>\r\n\r\n<nav class="mobile-nav" aria-label="Mobile navigation">\r\n  <a class="active" routerLink="/">\r\n    <svg viewBox="0 0 24 24"><path d="m3 11 9-8 9 8v9h-6v-6H9v6H3Z"></path></svg>\r\n    <span>Home</span>\r\n  </a>\r\n  <a routerLink="/search">\r\n    <svg viewBox="0 0 24 24"><circle cx="11" cy="11" r="7"></circle><path d="m20 20-4-4"></path></svg>\r\n    <span>Search</span>\r\n  </a>\r\n  <button type="button">\r\n    <svg viewBox="0 0 24 24"><path d="M3 4h2l2.3 10.1a2 2 0 0 0 2 1.5h7.9a2 2 0 0 0 2-1.6L21 7H6"></path><circle cx="10" cy="20" r="1"></circle><circle cx="18" cy="20" r="1"></circle></svg>\r\n    <span>Cart</span>\r\n  </button>\r\n  <a routerLink="/auth/login">\r\n    <svg viewBox="0 0 24 24"><circle cx="12" cy="8" r="4"></circle><path d="M4 21a8 8 0 0 1 16 0"></path></svg>\r\n    <span>Profile</span>\r\n  </a>\r\n</nav>\r\n', styles: ['/* src/app/features/home/pages/home/home.css */\n.home-page {\n  min-height: calc(100vh - 78px);\n  padding: 34px max(32px, calc((100vw - 1240px) / 2)) 80px;\n  background: var(--home-bg);\n}\n.mobile-location,\n.mobile-search,\n.mobile-nav {\n  display: none;\n}\n.browse-section {\n  margin-top: 50px;\n  scroll-margin-top: 96px;\n}\n.browse-section:first-of-type {\n  margin-top: 0;\n}\n.section-heading {\n  margin-bottom: 20px;\n  display: flex;\n  align-items: end;\n  justify-content: space-between;\n  gap: 20px;\n}\n.section-heading h1,\n.section-heading h2 {\n  margin: 4px 0 0;\n  color: var(--text);\n  font-family: "Playfair Display", serif;\n  font-size: clamp(25px, 3vw, 36px);\n}\n.step-label {\n  color: var(--brand);\n  font-size: 11px;\n  font-weight: 700;\n  letter-spacing: 0.18em;\n  text-transform: uppercase;\n}\n.section-count {\n  color: var(--text-soft);\n  font-size: 13px;\n}\n.category-grid {\n  display: grid;\n  grid-template-columns: repeat(4, 1fr);\n  gap: 18px;\n}\n.category-card,\n.store-card {\n  padding: 0;\n  overflow: hidden;\n  border: 1px solid var(--border);\n  background: var(--surface);\n  color: var(--text);\n  text-align: left;\n  cursor: pointer;\n  box-shadow: var(--shadow-sm);\n  transition:\n    transform 180ms ease,\n    border 180ms ease,\n    box-shadow 180ms ease;\n}\n.category-card {\n  position: relative;\n  aspect-ratio: 1;\n  border-radius: 18px;\n}\n.category-card:hover,\n.store-card:hover {\n  transform: translateY(-3px);\n}\n.category-card.active,\n.store-card.active {\n  border-color: var(--brand);\n  box-shadow: 0 0 0 2px color-mix(in srgb, var(--brand) 16%, transparent), var(--shadow-sm);\n}\n.card-photo {\n  position: absolute;\n  inset: 0;\n  display: block;\n  background-image:\n    linear-gradient(\n      180deg,\n      transparent 25%,\n      rgba(15, 9, 4, 0.78) 100%),\n    url(/assets/images/talabaty-food-table.png);\n  background-size: auto 260%;\n}\n.category-copy {\n  position: absolute;\n  z-index: 1;\n  left: 18px;\n  bottom: 16px;\n  display: grid;\n  color: #fff;\n}\n.category-copy strong {\n  font-size: 20px;\n}\n.category-copy small {\n  margin-top: 2px;\n  color: rgba(255, 255, 255, 0.74);\n}\n.selected-dot {\n  position: absolute;\n  z-index: 2;\n  top: 14px;\n  right: 14px;\n  width: 19px;\n  height: 19px;\n  border: 5px solid rgba(255, 255, 255, 0.85);\n  border-radius: 50%;\n  background: transparent;\n}\n.category-card.active .selected-dot {\n  background: var(--brand-strong);\n}\n.store-grid {\n  display: grid;\n  grid-template-columns: repeat(3, 1fr);\n  gap: 20px;\n}\n.store-card {\n  border-radius: 20px;\n}\n.store-photo {\n  position: relative;\n  height: 190px;\n  display: block;\n  background-image: url(/assets/images/talabaty-food-table.png);\n  background-size: auto 210%;\n}\n.rating,\n.eta {\n  position: absolute;\n  top: 13px;\n  padding: 6px 9px;\n  border-radius: 9px;\n  background: rgba(255, 255, 255, 0.92);\n  color: #2b251f;\n  font-size: 12px;\n  font-weight: 700;\n  -webkit-backdrop-filter: blur(8px);\n  backdrop-filter: blur(8px);\n}\n.rating {\n  left: 13px;\n}\n.eta {\n  right: 13px;\n  background: rgba(26, 22, 18, 0.8);\n  color: #fff;\n}\n.store-info {\n  min-height: 88px;\n  display: flex;\n  align-items: center;\n  justify-content: space-between;\n  gap: 12px;\n  padding: 16px;\n}\n.store-info > span:first-child {\n  display: grid;\n}\n.store-info strong {\n  font-size: 16px;\n}\n.store-info small {\n  margin-top: 5px;\n  color: var(--text-soft);\n}\n.delivery {\n  flex: 0 0 auto;\n  padding: 7px 9px;\n  border-radius: 9px;\n  background: var(--brand-soft);\n  color: var(--brand);\n  font-size: 10px;\n  font-weight: 700;\n}\n.product-grid {\n  display: grid;\n  grid-template-columns: repeat(3, 1fr);\n  gap: 20px;\n}\n.product-card {\n  overflow: hidden;\n  border: 1px solid var(--border);\n  border-radius: 20px;\n  background: var(--surface);\n  box-shadow: var(--shadow-sm);\n}\n.product-photo {\n  position: relative;\n  height: 180px;\n  background-image: url(/assets/images/talabaty-food-table.png);\n  background-size: auto 230%;\n}\n.product-info {\n  min-height: 165px;\n  padding: 18px;\n  display: flex;\n  flex-direction: column;\n  justify-content: space-between;\n}\n.product-info h3 {\n  margin: 0;\n  font-size: 17px;\n}\n.product-info p {\n  min-height: 42px;\n  margin: 7px 0 0;\n  color: var(--text-soft);\n  font-size: 13px;\n  line-height: 1.55;\n}\n.product-bottom {\n  margin-top: 18px;\n  display: flex;\n  align-items: center;\n  justify-content: space-between;\n  color: var(--brand);\n}\n.product-bottom button {\n  width: 38px;\n  height: 38px;\n  border: 0;\n  border-radius: 12px;\n  background: var(--brand);\n  color: #fff;\n  font-size: 24px;\n  line-height: 1;\n  cursor: pointer;\n}\n.empty-state {\n  grid-column: 1 / -1;\n  padding: 44px;\n  border: 1px dashed var(--border);\n  border-radius: 18px;\n  color: var(--text-soft);\n  text-align: center;\n}\n@media (max-width: 900px) {\n  .category-grid {\n    grid-template-columns: repeat(2, 1fr);\n  }\n  .store-grid,\n  .product-grid {\n    grid-template-columns: repeat(2, 1fr);\n  }\n}\n@media (max-width: 600px) {\n  .home-page {\n    width: 100%;\n    min-height: 100vh;\n    padding: 0 18px 105px;\n    overflow-x: hidden;\n  }\n  .mobile-location {\n    display: flex;\n    align-items: center;\n    gap: 8px;\n    padding: 8px 0 13px;\n    color: var(--brand);\n    font-size: 13px;\n  }\n  .mobile-location svg,\n  .mobile-search svg,\n  .mobile-nav svg {\n    width: 20px;\n    height: 20px;\n    fill: none;\n    stroke: currentColor;\n    stroke-linecap: round;\n    stroke-linejoin: round;\n    stroke-width: 1.8;\n  }\n  .mobile-location span {\n    color: var(--text-soft);\n  }\n  .mobile-location strong {\n    color: var(--brand);\n  }\n  .mobile-search {\n    height: 50px;\n    display: flex;\n    align-items: center;\n    gap: 10px;\n    padding: 0 14px;\n    border-radius: 14px;\n    background: var(--surface-3);\n    color: var(--text-soft);\n  }\n  .mobile-search input {\n    min-width: 0;\n    flex: 1;\n    border: 0;\n    outline: 0;\n    background: transparent;\n    color: var(--text);\n  }\n  .browse-section {\n    margin-top: 40px;\n  }\n  .categories-section {\n    margin-top: 32px !important;\n  }\n  .section-heading {\n    min-width: 0;\n    align-items: center;\n    margin-bottom: 16px;\n  }\n  .section-heading h1,\n  .section-heading h2 {\n    font-family: "DM Sans", sans-serif;\n    font-size: 21px;\n    letter-spacing: -0.02em;\n  }\n  .section-count {\n    max-width: 42%;\n    overflow: hidden;\n    font-size: 11px;\n    text-align: right;\n    text-overflow: ellipsis;\n    white-space: nowrap;\n  }\n  .category-grid {\n    display: grid;\n    grid-template-columns: repeat(4, minmax(0, 1fr));\n    gap: 10px;\n    overflow: visible;\n    padding: 2px;\n  }\n  .category-card {\n    width: 100%;\n    height: auto;\n    min-width: 0;\n    aspect-ratio: 1;\n    border-radius: 14px;\n  }\n  .category-copy {\n    right: 5px;\n    bottom: 17%;\n    left: 5px;\n    text-align: center;\n  }\n  .category-copy strong {\n    font-size: clamp(11px, 3.3vw, 15px);\n  }\n  .category-copy small {\n    display: none;\n  }\n  .selected-dot {\n    top: 8%;\n    right: 12%;\n    width: 16px;\n    height: 16px;\n    border-width: 4px;\n  }\n  .store-grid {\n    display: grid;\n    grid-template-columns: 1fr;\n    gap: 14px;\n    overflow: visible;\n    padding: 2px;\n  }\n  .store-card {\n    width: 100%;\n    min-width: 0;\n  }\n  .store-photo {\n    height: 160px;\n  }\n  .store-info {\n    min-height: 82px;\n  }\n  .product-grid {\n    grid-template-columns: 1fr;\n    gap: 16px;\n  }\n  .product-card {\n    display: grid;\n    grid-template-columns: 132px 1fr;\n    min-height: 154px;\n  }\n  .product-photo {\n    height: 100%;\n    min-height: 154px;\n  }\n  .product-info {\n    min-height: 154px;\n    padding: 14px;\n  }\n  .product-info h3 {\n    font-size: 15px;\n  }\n  .product-info p {\n    min-height: auto;\n    font-size: 11px;\n  }\n  .product-bottom {\n    margin-top: 10px;\n    font-size: 13px;\n  }\n  .product-bottom button {\n    width: 33px;\n    height: 33px;\n    border-radius: 10px;\n  }\n  .mobile-nav {\n    position: fixed;\n    z-index: 40;\n    right: 0;\n    bottom: 0;\n    left: 0;\n    height: 72px;\n    display: grid;\n    grid-template-columns: repeat(4, 1fr);\n    border-top: 1px solid var(--border);\n    background: color-mix(in srgb, var(--home-bg) 94%, transparent);\n    box-shadow: 0 -10px 30px rgba(56, 37, 22, 0.08);\n    -webkit-backdrop-filter: blur(14px);\n    backdrop-filter: blur(14px);\n  }\n  .mobile-nav a,\n  .mobile-nav button {\n    display: flex;\n    flex-direction: column;\n    align-items: center;\n    justify-content: center;\n    gap: 4px;\n    border: 0;\n    background: transparent;\n    color: var(--text-soft);\n    font-size: 11px;\n  }\n  .mobile-nav .active {\n    color: var(--brand);\n  }\n}\n/*# sourceMappingURL=home.css.map */\n'] }]
  }], null, null);
})();
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(Home, { className: "Home", filePath: "src/app/features/home/pages/home/home.ts", lineNumber: 38 });
})();
export {
  Home
};
//# debugId=c47f931f-6d4a-54ef-ab55-35a2ebf62e20
//# sourceMappingURL=chunk-KQK7BJPG.js.map
