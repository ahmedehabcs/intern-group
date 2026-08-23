package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addon_groups")
@Getter
@Setter
@NoArgsConstructor
public class AddonGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer minSelections;

    @Column(nullable = false)
    private Integer maxSelections;

    @OneToMany(mappedBy = "addonGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItemAddon> addons = new ArrayList<>();

    @ManyToMany(mappedBy = "addonGroups")
    private List<MenuItem> menuItems = new ArrayList<>();
}