package facade;

import dto.HotelDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class HotelMapper {

    EntityManagerFactory emf;

    public HotelMapper(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<HotelDTO> getHotels(Integer lowestPrice, Integer highestPrice) {
        EntityManager em = emf.createEntityManager();

        //rip
        lowestPrice = lowestPrice == null ? Integer.MIN_VALUE : lowestPrice;
        highestPrice = highestPrice == null ? Integer.MAX_VALUE : highestPrice;

        //example for zip code
        TypedQuery<HotelDTO> query = em.createQuery("SELECT new dto.HotelDTO(h.id, h.name, h.description, h.rating, h.zipCode) FROM Hotel h where h.zipCode BETWEEN :lowestPrice AND :highestPrice", HotelDTO.class);
        query.setParameter("lowestPrice", lowestPrice);
        query.setParameter("highestPrice", highestPrice);

        List<HotelDTO> hotels = query.getResultList();
        return hotels;

    }

    public HotelDTO getHotel(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<HotelDTO> query = em.createQuery("SELECT new dto.HotelDTO(h) FROM Hotel h WHERE h.id = :id", HotelDTO.class);
        query.setParameter("id", id);
        HotelDTO hotelDTO;
        try {
            hotelDTO = query.getSingleResult();

        } catch (Exception e) {
            return null;
        }
        return hotelDTO;

    }

}
