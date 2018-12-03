package facade;

import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UserMapper {

    EntityManagerFactory emf;

    public UserMapper(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public User create(User user) {
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
        return user;

    }

    public User delete(String userName) {
        EntityManager em = emf.createEntityManager();
        User user;

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u where u.userName =:userName ", User.class);
        query.setParameter("userName", userName);

        try {
            user = query.getSingleResult();

            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();

        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();

        }
        return user;

    }

    public User update(User user) {

        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("UPDATE User u SET u.userPass =: userPass, u.roleList =:roleList where u.userName =:userName");
        
        query.setParameter("userName", user.getUserName());
        query.setParameter("userPass", user.getUserPass());
        query.setParameter("roleList", user.getRoleList());
        
        try {
            em.getTransaction().begin();
            query.executeUpdate();
            em.getTransaction().commit();
            
        } catch (NoResultException e) {
            return null;
        }finally{
            em.close();
        }
        
        return user;
    }

    public User getUser(String userName){
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u where u.userName =:userName", User.class);
        query.setParameter("userName", userName);
        User user;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }
    
    public List<User> getAllUsers(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> user = query.getResultList();
        return user;
        
    }
    
}
