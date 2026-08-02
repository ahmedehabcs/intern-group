package Entities.catalog;


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
}