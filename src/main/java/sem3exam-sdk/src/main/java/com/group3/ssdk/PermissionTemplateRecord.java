package com.group3.ssdk;

import java.util.List;
import java.util.Objects;

class PermissionTemplateRecord implements PermissionTemplate
{

    private String           id;
    private String           name;
    private String           message;
    private List<Permission> permissions;
    private ServiceRecord    service;

    public PermissionTemplateRecord()
    {
    }

    public PermissionTemplateRecord(String id, String name, String message, List<Permission> permissions, ServiceRecord service)
    {
        this.id = id;
        this.name = name;
        this.message = message;
        this.permissions = permissions;
        this.service = service;
    }

    @Override public String getId()
    {
        return id;
    }

    @Override public String getName()
    {
        return this.name;
    }

    public PermissionTemplateRecord setName(String name)
    {
        this.name = name;
        return this;
    }

    @Override public String getMessage()
    {
        return message;
    }

    @Override public List<Permission> getPermissions()
    {
        return permissions;
    }

    @Override public Service getService()
    {
        return service;
    }

    public PermissionTemplateRecord setId(String id)
    {
        this.id = id;
        return this;
    }

    public PermissionTemplateRecord setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public PermissionTemplateRecord setPermissions(List<Permission> permissions)
    {
        this.permissions = permissions;
        return this;
    }

    public PermissionTemplateRecord setService(ServiceRecord service)
    {
        this.service = service;
        return this;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionTemplateRecord that = (PermissionTemplateRecord) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(message, that.message) &&
               Objects.equals(permissions, that.permissions) &&
               Objects.equals(service, that.service);
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, name, message, permissions, service);
    }

    @Override public String toString()
    {
        return "PermissionTemplateRecord{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", message='" + message + '\'' +
               ", permissions=" + permissions +
               ", service=" + service +
               '}';
    }
}
