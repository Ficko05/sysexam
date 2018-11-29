/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cphotels.facade;

import cphotels.SuiteTest;
import dto.HotelDTO;
import facade.HotelMapper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dradrach
 */
public class HotelMapperT {
    
    private final EntityManagerFactory emf = SuiteTest.getEmf();
    private HotelMapper hotelMapper = new HotelMapper(emf);
    public HotelMapperT() {
        
    }
    
//    
//
//    /**
//     * Test of getHotelsByPrice method, of class HotelMapper.
//     */
//    @Test
//    public void testGetHotels() {
//        System.out.println("Test: getHotelsByPrice");
//        assertTrue(true);
//    }
//    
    /**
     * Test for getHotelsFromZip
     */
    @Test
    public void testGetHotelsFromZip(){
        System.out.println("Test: getHotelsFormZip");
        List<HotelDTO> listOfHotels = hotelMapper.getHotelsFromZip(1100);
        int result = listOfHotels.size();
        
        assertEquals(1, result);
    }
    
    
     @Test
    public void testGetHotelsBothNull() {
        
        List<HotelDTO> result = hotelMapper.getHotelsByPrice(null, null);

        assertEquals(5, result.size());

    }

    @Test
    public void testGetHotelsBothNonNull() {

        List<HotelDTO> result = hotelMapper.getHotelsByPrice(999, 1999);

        assertEquals(4, result.size());

    }

    @Test
    public void testGetHotelOneNull(){
        
        List<HotelDTO> result = hotelMapper.getHotelsByPrice(0, 1500);

        assertEquals(4, result.size());
        
    }
}
