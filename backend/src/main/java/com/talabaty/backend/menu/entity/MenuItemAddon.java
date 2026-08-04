package com.talabaty.backend.menu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_item_addons")
public class MenuItemAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double additionalPrice = 0.0;

    @Column(nullable = false, columnDefinition = "boolean default true")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(Double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public AddonGroup getAddonGroup() {
        return addonGroup;
    }

    public void setAddonGroup(AddonGroup addonGroup) {
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
