package cphotels;


import entity.Hotel;
import entity.Role;
import entity.Room;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dradrach
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
    HotelMapperT.class
})

public class SuiteTest {
private static EntityManagerFactory emf;
    
    @BeforeClass
    public static void SetupDB(){
        System.out.println("Setup DB");
        emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
//
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
        
//        //id, name, description, rating, zipCode, picture
        Hotel hotel1 = new Hotel(1, "firstHotel", "First Hotel", 10, 1000, new byte[1]);
        Hotel hotel2 = new Hotel(2, "secondHotel", "First Hotel", 10, 1000, new byte[1]);
        Hotel hotel3 = new Hotel(3, "thirdHotel", "First Hotel", 10, 1000, new byte[1]);
        Hotel hotel4 = new Hotel(4, "fourthHotel", "First Hotel", 10, 1100, new byte[1]);
        Hotel hotel5 = new Hotel(5, "fifthHotel", "Second Hotel", 20, 2000, new byte[1]);
        
        Room room1 = new Room(1, 100, hotel1);
        Room room2 = new Room(1, 200, hotel2);
        Room room3 = new Room(1, 300, hotel3);
        Room room4 = new Room(1, 400, hotel4);
        Room room5 = new Room(1, 500, hotel5);
        
        hotel1.addRoom(room1);
        hotel2.addRoom(room2);
        hotel3.addRoom(room3);
        hotel4.addRoom(room4);
        hotel5.addRoom(room5);
        
        em.persist(hotel1);
        em.persist(hotel2);
        em.persist(hotel3);
        em.persist(hotel4);
        em.persist(hotel5);
        
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
//        
        em.getTransaction().commit();
        
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }
    
    
}
