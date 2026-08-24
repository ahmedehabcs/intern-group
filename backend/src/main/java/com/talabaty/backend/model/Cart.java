package com.talabaty.backend.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ربط السلة بالعميل (سلة واحدة نشطة لكل عميل)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", unique = true, nullable = false)
    private CustomerProfile customer;

    // ربط السلة بالمطعم عشان نمنع الطلب من مطعمين في نفس الوقت
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // مجموع السعر (يُفضل حسابه ديناميكياً، بس ممكن نخزنه لتقليل اللود على الداتا بيز)
    private Double subtotal = 0.0;

    // ربط السلة بالعناصر اللي جواها
    // orphanRemoval = true مهمة جداً عشان لو شلنا أكلة من السلة تتمسح من الداتا بيز فوراً
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public Cart() {}

    public Cart(List<CartItem> items, Double subtotal, Restaurant restaurant, CustomerProfile customer) {
        this.items = items;
        this.subtotal = subtotal;
        this.restaurant = restaurant;
        this.customer = customer;
    }











    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                ", subtotal=" + subtotal +
                ", items=" + items +
                '}';
    }
}
