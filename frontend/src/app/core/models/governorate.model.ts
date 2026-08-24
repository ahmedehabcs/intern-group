/**
 * Reference data from GET /api/governorates.
 *
 * Shared rather than feature-local: both the customer address form and the
 * admin restaurant form need the id/name pairs, because AddressRequest and
 * CreateRestaurantRequest each take a governorateId.
 */
export interface GovernorateResponse {
  id: number;
  name: string;
}
