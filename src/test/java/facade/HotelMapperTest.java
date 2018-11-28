package facade;

import dto.HotelDTO;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HotelMapperTest {

    private TestDB testDB = new TestDB();;

    @Before
    public void setUp() {
        testDB.setupDB();
    }

    /**
     * Test of getHotels method, of class HotelMapper.
     */
    @Test
    public void testGetHotelsBothNull() {
        HotelMapper hotelmapper = new HotelMapper(testDB.emf);
        List<HotelDTO> result = hotelmapper.getHotels(null, null);

        assertEquals(2, result.size());

    }

    @Test
    public void testGetHotelsBothNonNull() {

        HotelMapper hotelmapper = new HotelMapper(testDB.emf);
        List<HotelDTO> result = hotelmapper.getHotels(1000, 2000);

        assertEquals(2, result.size());

    }

    @Test
    public void testGetHotelOneNull(){
        
        HotelMapper hotelmapper = new HotelMapper(testDB.emf);
        List<HotelDTO> result = hotelmapper.getHotels(null, 1500);

        assertEquals(1, result.size());
        
    }
    
}
