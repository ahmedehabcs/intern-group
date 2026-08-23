package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@NoArgsConstructor
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
    @JoinColumn(name = "menu_section_id", nullable = false)
    private MenuSection menuSection;

    @ManyToMany
    @JoinTable(
            name = "menu_item_addon_groups",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "addon_group_id")
    )
    private List<AddonGroup> addonGroups = new ArrayList<>();

    public MenuItem(
            String name,
            String description,
            Double basePrice,
            String imageUrl,
            Boolean isAvailable,
            MenuSection menuSection,
            List<AddonGroup> addonGroups
    ) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
        this.menuSection = menuSection;
        this.addonGroups = addonGroups;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}