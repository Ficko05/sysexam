/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cphotels;

import entity.Order;
import facade.OrderMapper;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Dradrach
 */
public class OrderMapperT {
    private final EntityManagerFactory emf = SuiteTest.getEmf();
    private OrderMapper om = new OrderMapper(emf);

    public OrderMapperT() {
    }
    
    @Test
    public void testGetOrdersFromUserExisting() {
        String username = "user";
        
        List<Order> orders = om.getOrdersFromUser(username);
        
        assertEquals(2, orders.size());
    }
    
}
