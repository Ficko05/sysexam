/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dradrach
 */
public class HotelMapperIT {
    
    public HotelMapperIT() {
        
    }
    
    
    @Before
    public void setUp() {
       new TestDB().setupDB();
    }

    /**
     * Test of getHotels method, of class HotelMapper.
     */
    @org.junit.Test
    public void testGetHotels() {
    }
    
}
