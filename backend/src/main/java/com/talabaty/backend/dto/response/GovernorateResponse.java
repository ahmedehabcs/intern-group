package com.talabaty.backend.dto.response;

/**
 * A governorate the client can pick when writing an address or a restaurant.
 *
 * <p>Both {@code AddressRequest} and {@code CreateRestaurantRequest} take a
 * {@code governorateId}, so a client needs the id/name pairs to offer a choice
 * instead of asking someone to type a raw number.
 */
public record GovernorateResponse(
        Long id,
        String name
) {
}
