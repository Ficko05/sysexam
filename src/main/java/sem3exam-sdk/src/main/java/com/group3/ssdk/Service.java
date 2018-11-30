package com.group3.ssdk;

/**
 * Represents an entity that obtains permissions from users
 * to perform operations on behalf of that user.
 */
public interface Service
{

    /**
     * Returns the id of the service.
     *
     * @return The id of the service.
     */
    Integer getId();

    /**
     * Returns the name of the service. This value is unique to the authentication server.
     * Also used by the service when authenticating to the authentication server.
     *
     * @return The name of the service.
     */
    String getName();

    /**
     * Returns the current status of the service.
     *
     * @return The current status of the service.
     */
    Status getStatus();

    enum Status
    {

        /**
         * The service account was created.
         */
        PENDING,

        /**
         * The service account is enabled and can therefor perform operations.
         */
        ENABLED,

        /**
         * The service account is disabled and can therefor not perform operations.
         */
        DISABLED
    }
}
