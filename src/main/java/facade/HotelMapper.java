package facade;

import dto.HotelDTO;
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

    public List<HotelDTO> getHotels() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<HotelDTO> query = em.createNamedQuery("Hotel.findAllDTO", HotelDTO.class);

        List<HotelDTO> hotels = query.getResultList();
        return hotels;
    }

    public HotelDTO getHotel(int id) {
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<HotelDTO> query = em.createQuery("TODO",HotelDTO.class);
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
