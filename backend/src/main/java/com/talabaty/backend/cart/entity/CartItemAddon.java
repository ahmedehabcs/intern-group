package com.talabaty.backend.cart.entity;

import com.talabaty.backend.Entities.catalog.MenuItemAddon;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_addons")
public class CartItemAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer quantity = 1;


    private Double priceAtAddition = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_item_id", nullable = false)
    private CartItem cartItem;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_addon_id", nullable = false)
    private MenuItemAddon menuItemAddon;

    public CartItemAddon() {}

    public CartItemAddon(MenuItemAddon menuItemAddon, CartItem cartItem, Double priceAtAddition, Integer quantity) {
        this.menuItemAddon = menuItemAddon;
        this.cartItem = cartItem;
        this.priceAtAddition = priceAtAddition;
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

    public Double getPriceAtAddition() {
        return priceAtAddition;
    }

    public void setPriceAtAddition(Double priceAtAddition) {
        this.priceAtAddition = priceAtAddition;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public MenuItemAddon getMenuItemAddon() {
        return menuItemAddon;
    }

    public void setMenuItemAddon(MenuItemAddon menuItemAddon) {
        this.menuItemAddon = menuItemAddon;
    }

    @Override
    public String toString() {
        return "CartItemAddon{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", priceAtAddition=" + priceAtAddition +
                ", cartItem=" + cartItem +
                ", menuItemAddon=" + menuItemAddon +
                '}';
    }
}
