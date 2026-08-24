package com.talabaty.backend.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_addons")
@Getter
@Setter
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
