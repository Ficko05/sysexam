package com.group3.ssdk;

import java.util.List;

class AuthResponseRecord implements AuthResponse
{

    private AuthRequestRecord request;
    private ServiceUserRecord user;
    private String            token;
    private List<Permission>  permissions;

    public AuthResponseRecord()
    {

    }

    public AuthResponseRecord(AuthRequestRecord request, ServiceUserRecord user, String token, List<Permission> permissions)
    {
        this.request = request;
        this.user = user;
        this.token = token;
        this.permissions = permissions;
    }

    @Override public AuthRequest getRequest()
    {
        return request;
    }

    @Override public ServiceUser getUser()
    {
        return user;
    }

    @Override public String getToken()
    {
        return token;
    }

    @Override public List<Permission> getPermissions()
    {
        return permissions;
    }

    public AuthResponseRecord setRequest(AuthRequestRecord request)
    {
        this.request = request;
        return this;
    }

    public AuthResponseRecord setUser(ServiceUserRecord user)
    {
        this.user = user;
        return this;
    }

    public AuthResponseRecord setToken(String token)
    {
        this.token = token;
        return this;
    }

    public AuthResponseRecord setPermissions(List<Permission> permissions)
    {
        this.permissions = permissions;
        return this;
    }
}
