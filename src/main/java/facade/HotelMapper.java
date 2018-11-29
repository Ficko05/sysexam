package facade;

import dto.HotelDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Kristian
 */
public class HotelMapper {

    EntityManagerFactory emf;

    public HotelMapper(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<HotelDTO> getHotels(Integer lowetPrice, Integer highestPrice) {
        EntityManager em = emf.createEntityManager();

        if (lowetPrice == null && highestPrice == null){
            
        TypedQuery<HotelDTO> query = em.createQuery("SELECT new dto.HotelDTO(h.id, h.name, h.description, h.rating, h.zipCode) FROM Hotel h", HotelDTO.class);
        List<HotelDTO> hotels = query.getResultList();
        return hotels;
        }else{
           //example for zip code
            TypedQuery<HotelDTO> query = em.createQuery("SELECT new dto.HotelDTO(h.id, h.name, h.description, h.rating, h.zipCode) FROM Hotel h where h.zipCode BETWEEN :lowestPrice AND :highestPrice", HotelDTO.class);
            query.setParameter("lowetPrice", lowetPrice);
            query.setParameter("highestPrice", highestPrice);
            
        List<HotelDTO> hotels = query.getResultList();
        return hotels;
        }
        

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
    /**
     * Method takes a ZipCode and returns a list of all matching Hotel(DTO)
     * with given ZipCode
     * @param zipCode
     * @return 
     */
    public List<HotelDTO> getHotelFromZip(int zipCode) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<HotelDTO> query = em.createQuery("SELECT new dto.HotelDTO(h.id, h.name, h.description, h.rating, h.zipCode) From Hotel h WHERE h.zipCode =:zipCode", HotelDTO.class);
        query.setParameter("zipCode", zipCode);
        List<HotelDTO> listHotelDTO;
        try {
            listHotelDTO = query.getResultList();

        } catch (Exception e) {
            return null;
        }
        return listHotelDTO;
    }

}
