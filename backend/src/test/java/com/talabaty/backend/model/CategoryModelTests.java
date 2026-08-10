package com.talabaty.backend.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryModelTests {

    @Test
    void restaurantCategoriesUseTheCuisineJoinTable() throws NoSuchFieldException {
        Field categories = Restaurant.class.getDeclaredField("categories");

        assertNotNull(categories.getAnnotation(ManyToMany.class));

        JoinTable joinTable = categories.getAnnotation(JoinTable.class);
        assertNotNull(joinTable);
        assertEquals("restaurant_categories", joinTable.name());
        assertEquals("restaurant_id", joinTable.joinColumns()[0].name());
        assertEquals("category_id", joinTable.inverseJoinColumns()[0].name());
    }

    @Test
    void categoryIsTheInverseSideOfTheRestaurantRelationship() throws NoSuchFieldException {
        Field restaurants = Category.class.getDeclaredField("restaurants");
        ManyToMany relationship = restaurants.getAnnotation(ManyToMany.class);

        assertNotNull(relationship);
        assertEquals("categories", relationship.mappedBy());
    }

    @Test
    void menuSectionsRemainASeparateRestaurantRelationship() throws NoSuchFieldException {
        Field menuSections = Restaurant.class.getDeclaredField("menuSections");
        OneToMany relationship = menuSections.getAnnotation(OneToMany.class);

        assertNotNull(relationship);
        assertEquals("restaurant", relationship.mappedBy());
        assertEquals("menu_sections", MenuSection.class.getAnnotation(Table.class).name());
    }

    @Test
    void menuItemsReferenceMenuSectionsRatherThanCategories() throws NoSuchFieldException {
        Field menuSection = MenuItem.class.getDeclaredField("menuSection");
        JoinColumn joinColumn = menuSection.getAnnotation(JoinColumn.class);

        assertNotNull(joinColumn);
        assertEquals("menu_section_id", joinColumn.name());
    }
}
