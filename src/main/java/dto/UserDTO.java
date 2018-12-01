package dto;

import entity.Role;
import entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String userName;
    
    private List<RoleDTO> roleList = new ArrayList();

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.userName = user.getUserName();
        for (Role role: user.getRoleList()) {
            roleList.add(new RoleDTO(role));
        }
    }

    public UserDTO(String userName) {
        this.userName = userName;

    }
    
    public static UserDTO getUserDTO(User user) {
        return new UserDTO(user);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    

}
