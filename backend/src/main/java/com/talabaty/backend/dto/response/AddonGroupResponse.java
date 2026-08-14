package com.talabaty.backend.dto.response;

import java.util.List;

public class AddonGroupResponse {

    private Long id;
    private String name;
    private Integer minSelections;
    private Integer maxSelections;
    private List<MenuItemAddonResponse> addons;

    public AddonGroupResponse() {
    }

    public AddonGroupResponse(
            Long id,
            String name,
            Integer minSelections,
            Integer maxSelections,
            List<MenuItemAddonResponse> addons
    ) {
        this.id = id;
        this.name = name;
        this.minSelections = minSelections;
        this.maxSelections = maxSelections;
        this.addons = addons;
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

    public List<MenuItemAddonResponse> getAddons() {
        return addons;
    }

    public void setAddons(List<MenuItemAddonResponse> addons) {
        this.addons = addons;
    }
}