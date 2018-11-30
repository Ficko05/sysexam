package com.group3.ssdk;

import java.time.LocalDateTime;

/**
 * Represents a user that authenticated through the authentication server.
 */
public interface ServiceUser
{

    /**
     * Returns the id of the user. This value is unique to users across the
     * authentication server.
     *
     * @return The id of the user.
     */
    Integer getId();

    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */
    String getName();

    /**
     * Returns the email address of the user.
     *
     * @return The email address of the user.
     */
    String getEmail();

    /**
     * Returns the moment in time when the user account was created.
     *
     * @return The moment in time when the user account was created.
     */
    LocalDateTime getCreatedAt();
}