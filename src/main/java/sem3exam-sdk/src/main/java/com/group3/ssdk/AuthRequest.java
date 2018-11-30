package com.group3.ssdk;

import java.time.LocalDateTime;

/**
 * Represents a request for authentication sent by the client
 * and forwarded to the authentication service by the service.
 */
public interface AuthRequest
{

    /**
     * Returns the id assigned to this authentication request by the authentication server.
     * <p>
     * The id is used when the user attempts to authenticate, and must therefor be
     * provided to the client.
     *
     * @return The id.
     */
    String getId();

    /**
     * Returns the http address called when the user authenticates as a response to this
     * request.
     *
     * @return The http address.
     */
    String getCallback();

    /**
     * Returns the service that requested authentication.
     *
     * @return The service that requested authentication.
     */
    Service getService();

    /**
     * Returns the status of the authentication request.
     *
     * @return The status of the authentication request.
     */
    Status getStatus();

    /**
     * Returns the moment in time when the authentication request was created by the
     * service.
     *
     * @return The moment in time when the authentication request was created by the
     * service.
     */
    LocalDateTime getCreatedAt();

    /**
     * Returns an optional permission template that should be accepted when the user
     * authenticates through the authentication server as a response to this request.
     *
     * @return The optional permission template.
     */
    PermissionTemplate getPermissionTemplate();

    enum Status
    {

        /**
         * The request has been created by the service, but the request has
         * not been used by a user yet.
         */
        PENDING,

        /**
         * The request was used successfully by a user.
         */
        COMPLETED
    }
}
