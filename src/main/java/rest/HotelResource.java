/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import dto.HotelDTO;
import entity.Hotel;
import facade.HotelMapper;
import imageHandling.DataUriEncoder;
import imageHandling.ImageType;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
     * @param lowestPrice
     * @param highestPrice
     * @return an instance of java.lang.String
     */
    @GET
    @Path("simple")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHotels(@QueryParam("lowestPrice") Integer lowestPrice,
            @QueryParam("highestPrice") Integer highestPrice) {

        List<Hotel> hotels = hm.getHotelsByPrice(lowestPrice, highestPrice);
        for (Hotel hotel : hotels) {
            hotel.setDescription(hotel.getDescription().substring(0, 40) + "...");
        }
        List<HotelDTO> hotelDTOs = HotelDTO.withoutPicture(hotels);

        return gson.toJson(hotelDTOs);
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
        Hotel hotel = hm.getHotel(id);

        if (hotel == null) {
            throw new Exception();
        }

        HotelDTO hotelDTO = HotelDTO.getHotelDTO(hotel);
        DataUriEncoder uriEncoder = new DataUriEncoder();
        String full = uriEncoder.encode(hotelDTO.getPicture(), ImageType.fromData(hotelDTO.getPicture()));
        hotelDTO.setPicture(null);
        JsonElement jsonElement = gson.toJsonTree(hotelDTO);
        jsonElement.getAsJsonObject().addProperty("picture", full);
        if (hotelDTO == null) {
            throw new Exception();//TODO
        }
        return Response.ok(gson.toJson(jsonElement)).build();
    }

    /**
     * returns list of Hotels in JSON from a given ZipS
     *
     * @param zip
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("zip/{zip}")
    public String getHotelFromZip(@PathParam("zip") int zip) throws Exception {
        List<Hotel> hotel = hm.getHotelsFromZip(zip);
        List<HotelDTO> hotelDTOs = HotelDTO.withoutPicture(hotel);

        for (HotelDTO hotelDTO : hotelDTOs) {
            //way to specific, should be done in front end (but is not because we couldn't get it to work with react bootstrap table)
            if(hotelDTO.getDescription().length() >= 40)
            hotelDTO.setDescription(hotelDTO.getDescription().substring(0, 40) + "...");
        }
        if (hotelDTOs == null || hotelDTOs.isEmpty()) {
            throw new Exception();//TODO
        }

        return gson.toJson(hotelDTOs);
    }

}
