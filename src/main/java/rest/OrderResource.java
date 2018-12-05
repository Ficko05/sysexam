/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dto.ReceivedBookingDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.OrderDTO;
import entity.Order;
import entity.Room;
import entity.User;
import facade.HotelMapper;
import facade.OrderMapper;
import facade.RoomMapper;
import facade.UserMapper;
import java.text.ParseException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author Dradrach
 */
@Path("order")
public class OrderResource {

    @Context
    private UriInfo context;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private final OrderMapper om;
    private final RoomMapper rm;
    private final UserMapper um;
    
     @Context
    SecurityContext securityContext;

    /**
     * Creates a new instance of BookingResource
     */
    public OrderResource() {
        om = new OrderMapper(emf);
        rm = new RoomMapper(emf);
        um = new UserMapper(emf);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String createOrder(String bookingDetails) throws ParseException {
        
        ReceivedBookingDTO booking = gson.fromJson(bookingDetails, ReceivedBookingDTO.class);
        
        //Should be on ID, not name I guess
        //Should be in a logic layer
        String username = securityContext.getUserPrincipal().getName();
        User user = um.getUser(username);
        Room room = rm.getRoom(booking.getRoomID());
        Order order = om.create(booking, room, user);
        
        OrderDTO orderDTO = OrderDTO.convert(order);
        
        return gson.toJson(orderDTO, OrderDTO.class);
    }
}