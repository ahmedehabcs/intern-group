package com.talabaty.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KitchenAddonGroupResponse {

    private Long id;
    private String name;
    private Integer minSelections;
    private Integer maxSelections;
    private List<KitchenAddonResponse> addons;

    public KitchenAddonGroupResponse() {
    }

    public KitchenAddonGroupResponse(
            Long id,
            String name,
            Integer minSelections,
            Integer maxSelections,
            List<KitchenAddonResponse> addons
    ) {
        this.id = id;
        this.name = name;
        this.minSelections = minSelections;
        this.maxSelections = maxSelections;
        this.addons = addons;
    }










}
