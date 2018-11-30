/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.group3.ssdk.*;
import dto.RequestSocialDTO;
import dto.SocialPost;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * REST Web Service
 *
 * @author Dradrach
 */
@Path("social")
public class SocialResource {

    @Context
    private UriInfo context;
    private static SocialSDK social;
    private static PermissionTemplate booking;
    private static Gson gson = SpecializedGson.create();
    private static String baseURL;
    private static String apiBaseURL;
    private static Map<String, Boolean> postedOnSocial = new HashMap<>();

    /**
     * Creates a new instance of SocialResource
     */
    public SocialResource() throws IOException {

    }

    private static void setUp() throws Exception {
        //The URL for socials REST API
        baseURL = "http://b36c4c9d.ngrok.io/semester-3-exam-rest/";
        
        apiBaseURL = baseURL + "api/";

        //The URL for the REST endpoint we tell social to call us back on
        String authCallback = "http://0fcab971.ngrok.io/sysexam/api/social/authentication_callback";

        //MemoryAuthRequestStore saves an ID when our user clicks the button (before we call social API) 
        //When social call us back after the user has logged in on their site social call with that id (needed because they
        //do not return the information when we call, they call us back afterwards, so we have no idea of knowing who they talk about without that id)
        //We can then check which user they return information for.
        social = new SocialSDK(apiBaseURL, authCallback, new MemoryAuthRequestStore());
        //Registers a user for our REST api (not our users) on social
        social.register("CPHotels", "1234");
        //Authentificates CPHotels on social, like a log in
        social.connect("CPHotels", "1234");
        //Our own naming, send through url, they have the template
        String templateName = "post_hotel_booking";
        //PermissionTemplate: En liste af permissions som kan genbruges. Permissions er noget brugeren giver os
        //tilladelse til at gøre
        booking = social.getTemplateByName(templateName);
        if (booking == null) {
            booking = social.createPermissionTemplate(templateName, "Post a booking for CPHotels", Arrays.asList(Permission.POST));
        }
    }

    //Endpoint received from client and notifies social (when end user want to log in/has clicked button)
    //Post because it creates something (on socials end, doesn't have to take in parameters)
    @POST
    @Path("request_authorization")
    @Produces(MediaType.APPLICATION_JSON)
    public Response onAuthResponse() throws IOException, Exception {
        if (booking == null) {
            setUp();
        }

        //AuthRequest to social, with permissions 
        AuthRequest request = social.requestAuth(booking, new Consumer<AuthResponse>() {
            @Override
            //Callback. This code is run when user has been authenticated on social. SO IT IS NOT RUN NOW!
            public void accept(AuthResponse authResponse) {
                try {
                    createPost(authResponse);
                    //true because it has now been posted on social. Second
                    postedOnSocial.put(authResponse.getRequest().getId(), true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        //false because it is not posted on social yet, the value is checked later. First
        postedOnSocial.put(request.getId(), false);
        
        //JSON social API want
        String authenticationURL = baseURL + "service-authentication.html" + "?request=" + request.getId();
        RequestSocialDTO requestSocial = new RequestSocialDTO(request.getId(), authenticationURL);

        return Response.ok().entity(gson.toJson(requestSocial)).build();
    }

    //Endpoint that receives a response when the end user authenticates on social 
    @POST
    @Path("authentication_callback")
    @Consumes(MediaType.APPLICATION_JSON)
    public String onAuthResponse(String content) throws Exception {

        if (booking == null) {
            setUp();
        }

        social.onAuthResponse(content);
        return null;
    }

    @GET
    @Path("posted_on_social")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkIfPostedOnSocial(@QueryParam("id") String userID) throws Exception {
        Boolean posted = postedOnSocial.get(userID);
        if (posted == null) {
            throw new Exception();
        }
        JsonObject response = new JsonObject();
        response.addProperty("isPosted", posted);
        return gson.toJson(response);
    }

    
    
    
    private void createPost(AuthResponse authResponse) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(apiBaseURL + "posts");

        SocialPost socialPost = new SocialPost("I've just booked a hotel on CPHotels!"); //should be hotelDTO (can also post images)

        String jsonPost = gson.toJson(socialPost);

        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        //header sending the token verifying which user we are
        request.setHeader("Authorization", "Bearer " + authResponse.getToken());
        request.setEntity(new StringEntity(jsonPost));

        client.execute(request); //returns response
        client.close();

    }
}
