/**
 * Entity &lt;-&gt; DTO mappers.
 *
 * <p>One mapper per aggregate, named after the model it converts
 * (e.g. {@code UserMapper}, {@code RestaurantMapper}). Mappers translate between
 * {@code com.talabaty.backend.model} and {@code com.talabaty.backend.dto}; they hold
 * no business rules and no repository access.
 */
package com.talabaty.backend.mapper;
