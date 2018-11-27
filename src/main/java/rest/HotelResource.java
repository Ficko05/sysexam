/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.HotelDTO;
import facade.HotelMapper;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Kristian
 */
@Path("hotel")
public class HotelResource {

    @Context
    private UriInfo context;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private final HotelMapper hm;

    /**
     * Creates a new instance of HotelResource
     */
    public HotelResource() {
        hm = new HotelMapper(emf);
    }

    /**
     * Retrieves representation of an instance of rest.HotelResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("simple")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHotels(@QueryParam("lowestPrice") Integer lowestPrice,
            @QueryParam("highestPrice") Integer highestPrice ){

        List<HotelDTO> hotels = hm.getHotels(lowestPrice, highestPrice);
        for (HotelDTO hotel : hotels) {
            hotel.setDescription(hotel.getDescription().substring(0, 40) + "...");
        }
        System.out.println(hotels);

        return gson.toJson(hotels);
    }

    /**
     * PUT method for updating or creating an instance of HotelResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getHotel(@PathParam("id") int id) throws Exception {
        HotelDTO hotelDTO = hm.getHotel(id);

        if (hotelDTO == null) {
            throw new Exception();//TODO
        }
        return Response.ok(gson.toJson(hotelDTO)).build();

    }

}
