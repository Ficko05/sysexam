package facade;

import entity.Hotel;
import entity.Role;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestDB {

    public EntityManagerFactory emf;

    public TestDB() {
        
    }

    //instead of running this before each test, you can just make a transaction in every test and then roll back
    public void setupDB() {
        if(emf.isOpen())
            emf.close();
        emf = Persistence.createEntityManagerFactory("test");
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
