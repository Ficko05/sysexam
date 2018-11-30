package com.group3.ssdk;

import java.util.List;

/**
 * Represents a list of permissions that can be requested by a service user.
 */
public interface PermissionTemplate
{

    /**
     * Returns the id of the permission template. The id is assigned by the authentication
     * server.
     *
     * @return The id of the permission template.
     */
    String getId();

    /**
     * Returns the name of the permission template. The name of the permission template is
     * unique to the service that creates the permission template.
     *
     * @return The name of the permission template.
     */
    String getName();

    /**
     * Returns a message that is provided to the user when they are prompted to accept
     * or reject the requested permissions.
     *
     * @return The message.
     */
    String getMessage();

    /**
     * Returns the permissions requested from the user.
     *
     * @return The permissions requested from the user.
     */
    List<Permission> getPermissions();

    /**
     * Returns the service that owns the template. This is the service that created the
     * template.
     *
     * @return The service that owns the template.
     */
    Service getService();
}
