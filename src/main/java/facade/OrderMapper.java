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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dradrach
 */
public class OrderMapper {
    EntityManagerFactory emf;

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

    public List<Order> getOrdersFromUser(String username) {
       EntityManager em = emf.createEntityManager();
       
       TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.user.userName = :username ORDER BY o.startDate DESC", Order.class);
       query.setParameter("username", username);
       

        return query.getResultList();
}
    
}
