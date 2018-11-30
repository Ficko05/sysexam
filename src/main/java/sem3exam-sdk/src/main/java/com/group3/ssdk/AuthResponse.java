package com.group3.ssdk;

import java.util.List;

/**
 * A response a to {@link AuthRequest}. The response occurs when a user
 * authenticates through the authentication server using a created
 * authentication request.
 */
public interface AuthResponse
{

    /**
     * Returns the request this response was a response to.
     *
     * @return The request this response was a response to.
     */
    AuthRequest getRequest();

    /**
     * Returns the service user that was authenticated.
     *
     * @return The service user that was authenticated.
     */
    ServiceUser getUser();

    /**
     * Returns the authentication token that can be used to perform requests on
     * behalf of the authenticated user.
     *
     * @return The authentication token.
     */
    String getToken();

    /**
     * Returns the permissions given to the service by the user.
     *
     * @return The permissions given to the service by the user.
     */
    List<Permission> getPermissions();
}
