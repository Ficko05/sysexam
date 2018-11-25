package facade;


import entity.Hotel;
import entity.Role;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dradrach
 */
public class TestDB {
    EntityManagerFactory emf;

    public TestDB() {
        emf = Persistence.createEntityManagerFactory("test");
    }
    
    public void setupDB() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        User user = new User("user", "test");
        user.addRole(userRole);
        User admin = new User("admin", "test");
        admin.addRole(adminRole);
        User both = new User("user_admin", "test");
        both.addRole(userRole);
        both.addRole(adminRole);
        
        //id, name, description, rating, zipCode, picture
        Hotel hotel1 = new Hotel(1, "firstHotel", "First Hotel", 10, 1000, new byte[1]);
        Hotel hotel2 = new Hotel(2, "secondHotel", "Second Hotel", 20, 2000, new byte[1]);
        
        em.persist(hotel1);
        em.persist(hotel2);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.getTransaction().commit();
    }
}