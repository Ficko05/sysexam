/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.HotelDTO;
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

public class HotelMapperT{
    
<<<<<<< HEAD:src/test/java/facade/HotelMapperT.java
    public HotelMapperT() {
        
    }
    /**
     * Test of getHotels method, of class HotelMapperT.
=======
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    public HotelMapperTest() {
        
    }
    
    
    @Before
    public void setUp() {
       new TestDB().setupDB();
       
    }

//    /**
//     * Test of getHotels method, of class HotelMapper.
//     */
//    @Test
//    public void testGetHotels() {
//        System.out.println("Test: getHotels");
//        assertTrue(true);
//    }
    
    /**
     * Test for getHotelFromZip
>>>>>>> hotelMapper:src/test/java/facade/HotelMapperTest.java
     */
    @Test
    public void testGetHotelsFromZip(){
        System.out.println("Test: getHotelsFormZip");
        HotelMapper hotelMap = new HotelMapper(emf);
        List<HotelDTO> listOfHotels = hotelMap.getHotelFromZip(1100);
        int result = listOfHotels.size();
        
        assertEquals(6, result);
    }
}
