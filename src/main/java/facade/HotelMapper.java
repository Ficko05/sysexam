package facade;

import entity.Hotel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class HotelMapper {

    EntityManagerFactory emf;

    public HotelMapper(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public List<Hotel> getHotels() {
        EntityManager em = emf.createEntityManager();
        
        //example for zip code
        TypedQuery<Hotel> query = em.createQuery("SELECT new entity.Hotel(h.id, h.name, h.description, h.rating, h.zipCode) FROM Hotel h", Hotel.class);

        List<Hotel> hotels = query.getResultList();
        return hotels;
    }

    public List<Hotel> getHotelsByPrice(Integer lowestPrice, Integer highestPrice) {
        EntityManager em = emf.createEntityManager();

        lowestPrice = lowestPrice == null ? Integer.MIN_VALUE : lowestPrice;
        highestPrice = highestPrice == null ? Integer.MAX_VALUE : highestPrice;

        //example for zip code
        TypedQuery<Hotel> query = em.createQuery("SELECT h FROM Hotel h where h.zipCode BETWEEN :lowestPrice AND :highestPrice", Hotel.class);
        query.setParameter("lowestPrice", lowestPrice);
        query.setParameter("highestPrice", highestPrice);

        List<Hotel> hotels = query.getResultList();
        return hotels;

    }

    public Hotel getHotel(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hotel> query = em.createQuery("SELECT h FROM Hotel h WHERE h.id = :id", Hotel.class);
        query.setParameter("id", id);
        Hotel hotelDTO;
        try {
            hotelDTO = query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
        return hotelDTO;

    }
    /**
     * Method takes a ZipCode and returns a list of all matching Hotel(DTO)
     * with given ZipCode
     * @param zipCode
     * @return 
     */
    public List<Hotel> getHotelsFromZip(int zipCode) {
        EntityManager em = emf.createEntityManager();
        //Should have multiple queries so ONLY the necessary data is retrieved
        TypedQuery<Hotel> query = em.createQuery("SELECT h FROM Hotel h WHERE h.zipCode =:zipCode", Hotel.class);
        
        query.setParameter("zipCode", zipCode);
        return query.getResultList();
    }

}
