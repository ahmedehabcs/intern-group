package Entities.catalog;

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
}
