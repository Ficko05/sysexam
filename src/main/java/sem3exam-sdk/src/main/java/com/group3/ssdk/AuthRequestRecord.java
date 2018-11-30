package com.group3.ssdk;

import java.time.LocalDateTime;
import java.util.Objects;

class AuthRequestRecord implements AuthRequest
{

    private String                   id;
    private String                   callback;
    private ServiceRecord            service;
    private Status                   status;
    private LocalDateTime            createdAt;
    private PermissionTemplateRecord template;

    public AuthRequestRecord()
    {

    }

    public AuthRequestRecord(String id, String callback, ServiceRecord service, Status status, LocalDateTime createdAt, PermissionTemplateRecord template)
    {
        this.id = id;
        this.callback = callback;
        this.service = service;
        this.status = status;
        this.createdAt = createdAt;
        this.template = template;
    }

    @Override public String getId()
    {
        return id;
    }

    @Override public String getCallback()
    {
        return callback;
    }

    @Override public Service getService()
    {
        return service;
    }

    @Override public Status getStatus()
    {
        return status;
    }

    @Override public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    @Override public PermissionTemplate getPermissionTemplate()
    {
        return template;
    }

    public AuthRequestRecord setId(String id)
    {
        this.id = id;
        return this;
    }

    public AuthRequestRecord setCallback(String callback)
    {
        this.callback = callback;
        return this;
    }

    public AuthRequestRecord setService(ServiceRecord service)
    {
        this.service = service;
        return this;
    }

    public AuthRequestRecord setStatus(Status status)
    {
        this.status = status;
        return this;
    }

    public AuthRequestRecord setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
        return this;
    }

    public AuthRequestRecord setTemplate(PermissionTemplateRecord template)
    {
        this.template = template;
        return this;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthRequestRecord that = (AuthRequestRecord) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(callback, that.callback) &&
               Objects.equals(service, that.service) &&
               status == that.status &&
               Objects.equals(createdAt, that.createdAt) &&
               Objects.equals(template, that.template);
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, callback, service, status, createdAt, template);
    }

    @Override public String toString()
    {
        return "AuthRequestRecord{" +
               "id='" + id + '\'' +
               ", callback='" + callback + '\'' +
               ", service=" + service +
               ", status=" + status +
               ", createdAt=" + createdAt +
               ", template=" + template +
               '}';
    }
}
