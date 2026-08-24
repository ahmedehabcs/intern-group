package com.talabaty.backend.model;

import lombok.Setter;

import lombok.Getter;
import lombok.experimental.Accessors;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_sections")
@Getter
@Setter
public class MenuSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "boolean default true")
    @Accessors(prefix = "is")
    private Boolean isActive = true;

    // علاقة القسم بالمطعم (القسم يخص مطعم واحد)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menuSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuSection() {}

    public MenuSection(String name, String description, Boolean isActive, Restaurant restaurant, List<MenuItem> menuItems) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.restaurant = restaurant;
        this.menuItems = menuItems;
    }
    // Getters and Setters...













    @Override
    public String toString() {
        return "MenuSection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", restaurant=" + restaurant +
                ", menuItems=" + menuItems +
                '}';
    }
}
