package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_sections")
@Getter
@Setter
@NoArgsConstructor
public class MenuSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive = true;

    // Link the menu section to its restaurant
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menuSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuSection(
            String name,
            String description,
            Boolean isActive,
            Restaurant restaurant,
            List<MenuItem> menuItems
    ) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.restaurant = restaurant;
        this.menuItems = menuItems;
    }
}