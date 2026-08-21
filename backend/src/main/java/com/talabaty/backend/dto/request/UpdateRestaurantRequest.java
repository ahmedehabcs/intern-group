package com.talabaty.backend.dto.request;

import java.math.BigDecimal;
import java.util.List;

public class UpdateRestaurantRequest {
    private String name;
    private String phone;
    private String email;
    private String address;
    private Long governorateId;
    private String description;
    private String logoUrl;
    private BigDecimal deliveryFee;
    private List<Long> categoryIds;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Long getGovernorateId() { return governorateId; }
    public void setGovernorateId(Long governorateId) { this.governorateId = governorateId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public BigDecimal getDeliveryFee() { return deliveryFee; }
    public void setDeliveryFee(BigDecimal deliveryFee) { this.deliveryFee = deliveryFee; }
    public List<Long> getCategoryIds() { return categoryIds; }
    public void setCategoryIds(List<Long> categoryIds) { this.categoryIds = categoryIds; }
}