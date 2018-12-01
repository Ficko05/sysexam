package dto;

import entity.Role;

public class RoleDTO {

    private String roleName;

    public RoleDTO() {
    }

    public RoleDTO(String roleName) {
        this.roleName = roleName;
        //this.userList = userList;
    }
    
    public RoleDTO(Role role){
       roleName = role.getRoleName();
    }
    
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
