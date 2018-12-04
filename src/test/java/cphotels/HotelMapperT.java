package cphotels;

import cphotels.SuiteTest;
import dto.HotelDTO;
import entity.Hotel;
import facade.HotelMapper;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.Test;
import static org.junit.Assert.*;
//
///**
// *
// * @author Dradrach
// */
public class HotelMapperT {
    
    private final EntityManagerFactory emf = SuiteTest.getEmf();
    private HotelMapper hotelMapper = new HotelMapper(emf);
    public HotelMapperT() {
        
    }
    
    

    /**
     * Test of getHotelsByPrice method, of class HotelMapper.
     */
    @Test
    public void testGetHotels() {
        System.out.println("Test: getHotelsByPrice");
        assertTrue(true);
    }
    
    /**
     * Test for getHotelsFromZip
     */
    @Test
    public void testGetHotelsFromZip(){
        System.out.println("Test: getHotelsFormZip");
        List<Hotel> listOfHotels = hotelMapper.getHotelsFromZip(1100);
        int result = listOfHotels.size();
        
        assertEquals(1, result);
    }
    
    
     @Test
    public void testGetHotelsBothNull() {
        
        List<Hotel> result = hotelMapper.getHotelsByPrice(null, null);

        assertEquals(5, result.size());

    }

    @Test
    public void testGetHotelsBothNonNull() {

        List<Hotel> result = hotelMapper.getHotelsByPrice(199, 401);

        assertEquals(3, result.size());

    }

    @Test
    public void testGetHotelOneNull(){
        
        List<Hotel> result = hotelMapper.getHotelsByPrice(0, 201);

        assertEquals(2, result.size());
        
    }
}
