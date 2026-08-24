package com.talabaty.backend.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class RestaurantAdminResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String governorateName;
    private String description;
    private String logoUrl;
    private Boolean isActive;
    private BigDecimal deliveryFee;
    private List<String> categoryNames;

    public RestaurantAdminResponse(Long id, String name, String phone, String email, String address,
                                   String governorateName, String description, String logoUrl,
                                   Boolean isActive, BigDecimal deliveryFee, List<String> categoryNames) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.governorateName = governorateName;
        this.description = description;
        this.logoUrl = logoUrl;
        this.isActive = isActive;
        this.deliveryFee = deliveryFee;
        this.categoryNames = categoryNames;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getGovernorateName() { return governorateName; }
    public String getDescription() { return description; }
    public String getLogoUrl() { return logoUrl; }
    public Boolean getIsActive() { return isActive; }
    public BigDecimal getDeliveryFee() { return deliveryFee; }
    public List<String> getCategoryNames() { return categoryNames; }
}