package com.talabaty.backend.dto.response;

public class MenuItemSearchResponse {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Long restaurantId;
    private String restaurantName;
    private Long menuSectionId;
    private String menuSectionName;

    public MenuItemSearchResponse() {
    }

    public MenuItemSearchResponse(
            Long id,
            String name,
            String description,
            Double price,
            String imageUrl,
            Long restaurantId,
            String restaurantName,
            Long menuSectionId,
            String menuSectionName
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuSectionId = menuSectionId;
        this.menuSectionName = menuSectionName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }
    public Long getMenuSectionId() { return menuSectionId; }
    public void setMenuSectionId(Long menuSectionId) { this.menuSectionId = menuSectionId; }
    public String getMenuSectionName() { return menuSectionName; }
    public void setMenuSectionName(String menuSectionName) { this.menuSectionName = menuSectionName; }
}
