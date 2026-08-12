package com.talabaty.backend.dto.response;

public class MenuItemResponse {

    private Long id;
    private String name;
    private String description;
    private Double basePrice;
    private String imageUrl;

    public MenuItemResponse() {
    }

    public MenuItemResponse(
            Long id,
            String name,
            String description,
            Double basePrice,
            String imageUrl
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}