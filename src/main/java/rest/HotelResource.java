/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.HotelMapper;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

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
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHotels() {     
        return gson.toJson(hm.getHotels());
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
}
