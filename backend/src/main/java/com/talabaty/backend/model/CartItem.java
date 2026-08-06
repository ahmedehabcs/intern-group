package com.talabaty.backend.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity; // كمية الوجبة (مثلاً: 2 برجر)

    @Column(columnDefinition = "TEXT")
    private String specialInstructions; // تعليمات خاصة زي "بدون بصل"، "كاتشب زيادة"

    // السعر الإجمالي للوجبة دي (السعر الأساسي + الإضافات) * الكمية
    private Double itemTotalPrice = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    // الإضافات اللي العميل اختارها للوجبة دي تحديداً
    @OneToMany(mappedBy = "cartItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemAddon> selectedAddons = new ArrayList<>();

    public CartItem() {}

    public CartItem(List<CartItemAddon> selectedAddons, MenuItem menuItem, Cart cart, Double itemTotalPrice, String specialInstructions, Integer quantity) {
        this.selectedAddons = selectedAddons;
        this.menuItem = menuItem;
        this.cart = cart;
        this.itemTotalPrice = itemTotalPrice;
        this.specialInstructions = specialInstructions;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Double getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(Double itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public List<CartItemAddon> getSelectedAddons() {
        return selectedAddons;
    }

    public void setSelectedAddons(List<CartItemAddon> selectedAddons) {
        this.selectedAddons = selectedAddons;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", specialInstructions='" + specialInstructions + '\'' +
                ", itemTotalPrice=" + itemTotalPrice +
                ", cart=" + cart +
                ", menuItem=" + menuItem +
                ", selectedAddons=" + selectedAddons +
                '}';
    }
}
