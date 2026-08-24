package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;
import lombok.experimental.Accessors;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_item_addons")
@Getter
@Setter
public class MenuItemAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double additionalPrice = 0.0;

    @Column(nullable = false, columnDefinition = "boolean default true")
    @Accessors(prefix = "is")
    private Boolean isAvailable = true;

    // علاقة الإضافة بالمجموعة بتاعتها
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addon_group_id", nullable = false)
    private AddonGroup addonGroup;

    public MenuItemAddon() {}
    // Getters and Setters...

    public MenuItemAddon(String name, Double additionalPrice, Boolean isAvailable, AddonGroup addonGroup) {
        this.name = name;
        this.additionalPrice = additionalPrice;
        this.isAvailable = isAvailable;
        this.addonGroup = addonGroup;
    }











    @Override
    public String toString() {
        return "MenuItemAddon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", additionalPrice=" + additionalPrice +
                ", isAvailable=" + isAvailable +
                ", addonGroup=" + addonGroup +
                '}';
    }
}
