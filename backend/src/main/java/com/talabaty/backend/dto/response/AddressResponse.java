package com.talabaty.backend.dto.response;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class AddressResponse {

    private Long id;
    private String street;
    private String building;
    private String floor;
    private String apartment;
    private String city;
    private Long governorateId;
    private String governorateName;
    private boolean isDefault;


















}
