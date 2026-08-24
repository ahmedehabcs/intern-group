package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;
import lombok.experimental.Accessors;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
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
    @Accessors(prefix = "is")
    private Boolean isAvailable = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_section_id", nullable = false)
    private MenuSection menuSection;

    @ManyToMany
    @JoinTable(
            name = "menu_item_addon_groups",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "addon_group_id")
    )
    private List<AddonGroup> addonGroups = new ArrayList<>();

    public MenuItem() {}
    // Getters and Setters...

    public MenuItem(String name, String description, Double basePrice, String imageUrl, Boolean isAvailable, MenuSection menuSection, List<AddonGroup> addonGroups) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
        this.menuSection = menuSection;
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
                ", menuSection=" + menuSection +
                ", addonGroups=" + addonGroups +
                '}';
    }
}
