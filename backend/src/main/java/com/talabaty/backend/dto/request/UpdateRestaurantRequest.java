package com.talabaty.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
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

}
