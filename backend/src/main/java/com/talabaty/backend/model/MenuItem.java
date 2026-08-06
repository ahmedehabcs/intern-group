package com.talabaty.backend.model;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double basePrice;

    private String imageUrl;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isAvailable = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private MenuSection category;

    @ManyToMany
    @JoinTable(
            name = "menu_item_addon_groups",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "addon_group_id")
    )
    private List<AddonGroup> addonGroups = new ArrayList<>();

    public MenuItem() {}
    // Getters and Setters...

    public MenuItem(String name, String description, Double basePrice, String imageUrl, Boolean isAvailable, MenuSection category, List<AddonGroup> addonGroups) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
        this.category = category;
        this.addonGroups = addonGroups;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public MenuSection getCategory() {
        return category;
    }

    public void setCategory(MenuSection category) {
        this.category = category;
    }

    public List<AddonGroup> getAddonGroups() {
        return addonGroups;
    }

    public void setAddonGroups(List<AddonGroup> addonGroups) {
        this.addonGroups = addonGroups;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", basePrice=" + basePrice +
                ", imageUrl='" + imageUrl + '\'' +
                ", isAvailable=" + isAvailable +
                ", category=" + category +
                ", addonGroups=" + addonGroups +
                '}';
    }
}
