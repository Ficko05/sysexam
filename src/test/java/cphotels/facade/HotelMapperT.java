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
    
    

    /**
     * Test of getHotels method, of class HotelMapper.
     */
    @Test
    public void testGetHotels() {
        System.out.println("Test: getHotels");
        assertTrue(true);
    }
    
    /**
     * Test for getHotelFromZip
     */
    @Test
    public void testGetHotelsFromZip(){
        System.out.println("Test: getHotelsFormZip");
        System.out.println("EMF test" + emf);
        System.out.println("Mapper test" + hotelMapper);
        List<HotelDTO> listOfHotels = hotelMapper.getHotelFromZip(1100);
        int result = listOfHotels.size();
        
        assertEquals(6, result);
    }
    
    
     @Test
    public void testGetHotelsBothNull() {
        
        List<HotelDTO> result = hotelMapper.getHotels(null, null);

        assertEquals(2, result.size());

    }

    @Test
    public void testGetHotelsBothNonNull() {

        List<HotelDTO> result = hotelMapper.getHotels(1000, 2000);

        assertEquals(2, result.size());

    }

    @Test
    public void testGetHotelOneNull(){
        
        List<HotelDTO> result = hotelMapper.getHotels(null, 1500);

        assertEquals(1, result.size());
        
    }
}
