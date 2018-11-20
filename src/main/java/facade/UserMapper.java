package facade;

import dto.UserDTO;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UserMapper {

    EntityManagerFactory emf;

    public UserMapper(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UserDTO create(User user) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
        return new UserDTO(user);

    }

    public UserDTO delete(String userName) {
        EntityManager em = emf.createEntityManager();
        UserDTO userDTO;

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u where u.userName =:userName ", User.class);
        query.setParameter("userName", userName);

        try {
            User user = query.getSingleResult();
            userDTO = new UserDTO(user);

            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();

        } catch (Exception e) {
            return null;
        } finally {
            em.close();

        }
        return userDTO;

    }

    public UserDTO update(User user) {

        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("UPDATE USER u SET u.userPass =: userPass, u.roleList =:roleList where u.userName =:userName");
        
        query.setParameter("userName", user.getUserName());
        query.setParameter("userPass", user.getUserPass());
        query.setParameter("roleList", user.getRoleList());
        
        try {
            em.getTransaction().begin();
            query.executeUpdate();
            em.getTransaction().commit();
            
        } catch (Exception e) {
            return null;
        }finally{
            em.close();
        }
        
        return new UserDTO(user);
    }

    public UserDTO getUser(String userName){
        EntityManager em = emf.createEntityManager();
        TypedQuery<UserDTO> query = em.createQuery("SELECT new DTO.UserDTO(u) FROM User u where u.userName =: userName", UserDTO.class);
        query.setParameter("userName", userName);
        UserDTO user;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return user;
    }
    
    public List<UserDTO> getAllUsers(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT new DTO.UserDTO(u) FROM User u", UserDTO.class);
        List<UserDTO> user = query.getResultList();
        return user;
        
    }
    
}
