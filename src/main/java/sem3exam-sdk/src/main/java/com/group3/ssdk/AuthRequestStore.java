package com.group3.ssdk;

/**
 * Stores authentication requests.
 */
public interface AuthRequestStore
{

    /**
     * Inserts the provided request into the store.
     *
     * @param request The request to insert into the store.
     * @return The authentication request replaced by the inserted authentication request.
     * When the insertion does not replace any element, {@code null} is returned.
     */
    AuthRequest store(AuthRequest request);

    /**
     * Checks if an authentication request with the provided id exists in the store.
     *
     * @param id The id of the authentication request that must by contained in the store.
     * @return {@code true} if an authentication request with the provided id exists in the store.
     */
    boolean has(String id);

    /**
     * Returns the stored authentication request with the provided id.
     *
     * @param id The id of the stored authentication request to return.
     * @return The stores authentication request with the provided id, {@code null} when
     * no such record exists.
     */
    AuthRequest get(String id);

    /**
     * Removes the authentication request with the provided id from the store.
     *
     * @param id The id of the authentication request to return.
     * @return The removed authentication request, {@code null} when no request was removed.
     */
    AuthRequest delete(String id);
}
