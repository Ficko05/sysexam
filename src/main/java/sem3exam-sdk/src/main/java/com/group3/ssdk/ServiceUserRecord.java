package com.group3.ssdk;

import java.time.LocalDateTime;
import java.util.Objects;

class ServiceUserRecord implements ServiceUser
{

    private Integer       id;
    private String        name;
    private String        email;
    private LocalDateTime createdAt;

    public ServiceUserRecord()
    {

    }

    public ServiceUserRecord(Integer id, String name, String email, LocalDateTime createdAt)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    @Override public Integer getId()
    {
        return id;
    }

    @Override public String getName()
    {
        return name;
    }

    @Override public String getEmail()
    {
        return email;
    }

    @Override public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceUserRecord that = (ServiceUserRecord) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(email, that.email) &&
               Objects.equals(createdAt, that.createdAt);
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, name, email, createdAt);
    }

    @Override public String toString()
    {
        return "ServiceUserRecord{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }
}
