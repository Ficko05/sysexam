/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.ReceivedBookingDTO;
import entity.Order;
import entity.Room;
import entity.User;
import java.text.ParseException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dradrach
 */
public class OrderMapper {
    EntityManagerFactory emf;
    RoomMapper rm = new RoomMapper(emf);
    UserMapper um = new UserMapper(emf);

    public OrderMapper(EntityManagerFactory emf) {
        this.emf = emf;
        
    }

    public Order create(ReceivedBookingDTO booking, Room room, User user) throws ParseException {
        EntityManager em = emf.createEntityManager();
        
        Order order = new Order(user, booking.getStartDate(), booking.getDays(), room);
        
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
        return order;
    }
}
