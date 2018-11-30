package com.group3.ssdk;

import java.util.Objects;

class ServiceRecord implements Service
{

    private Integer id;
    private String  name;
    private Status  status;

    public ServiceRecord()
    {
    }

    public ServiceRecord(Integer id, String name, Status status)
    {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Override public Integer getId()
    {
        return id;
    }

    @Override public String getName()
    {
        return name;
    }

    @Override public Status getStatus()
    {
        return status;
    }

    public ServiceRecord setId(Integer id)
    {
        this.id = id;
        return this;
    }

    public ServiceRecord setName(String name)
    {
        this.name = name;
        return this;
    }

    public ServiceRecord setStatus(Status status)
    {
        this.status = status;
        return this;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRecord that = (ServiceRecord) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               status == that.status;
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, name, status);
    }

    @Override public String toString()
    {
        return "ServiceRecord{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", status=" + status +
               '}';
    }
}
