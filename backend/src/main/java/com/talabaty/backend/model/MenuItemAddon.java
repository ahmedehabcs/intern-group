package com.talabaty.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menu_item_addons")
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addon_group_id", nullable = false)
    private AddonGroup addonGroup;

    public MenuItemAddon(
            String name,
            Double additionalPrice,
            Boolean isAvailable,
            AddonGroup addonGroup
    ) {
        this.name = name;
        this.additionalPrice = additionalPrice;
        this.isAvailable = isAvailable;
        this.addonGroup = addonGroup;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}