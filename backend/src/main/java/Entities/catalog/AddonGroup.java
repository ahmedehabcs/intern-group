package Entities.catalog;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addon_groups")
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

    public AddonGroup() {}

    public AddonGroup(String name, Integer minSelections, Integer maxSelections, List<MenuItemAddon> addons, List<MenuItem> menuItems) {
        this.name = name;
        this.minSelections = minSelections;
        this.maxSelections = maxSelections;
        this.addons = addons;
        this.menuItems = menuItems;
    }
    // Getters and Setters...

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

    public Integer getMinSelections() {
        return minSelections;
    }

    public void setMinSelections(Integer minSelections) {
        this.minSelections = minSelections;
    }

    public Integer getMaxSelections() {
        return maxSelections;
    }

    public void setMaxSelections(Integer maxSelections) {
        this.maxSelections = maxSelections;
    }

    public List<MenuItemAddon> getAddons() {
        return addons;
    }

    public void setAddons(List<MenuItemAddon> addons) {
        this.addons = addons;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "AddonGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minSelections=" + minSelections +
                ", maxSelections=" + maxSelections +
                ", addons=" + addons +
                ", menuItems=" + menuItems +
                '}';
    }
}
