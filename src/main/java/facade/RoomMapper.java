/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Hotel;
import entity.Room;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dradrach
 */
public class RoomMapper {
      EntityManagerFactory emf;

    public RoomMapper(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Room getRoom(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r WHERE r.id = :id", Room.class);
        query.setParameter("id", id);
        Room room;
        try {
            room = query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
        return room;

    }
}
