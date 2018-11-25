package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.UserDTO;
import entity.User;
import entity.Role;
import facade.UserMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import threads.JsonConsumer;
import threads.JsonProducer;
import utils.SetupTestUsers;

@Path("info")
public class DemoResource {

    @Context
    private UriInfo context;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private final UserMapper um;

    @Context
    SecurityContext securityContext;

    public DemoResource() {
//        String[] arr = {};
//        SetupTestUsers.main(arr);
        
        um = new UserMapper(emf);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String user = securityContext.getUserPrincipal().getName();
        return "\"Welcome back, " + user + "\"";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String user = securityContext.getUserPrincipal().getName();
        return "\"You're logged in as " + user + "\"";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("swapi/{id}/")
    public String getDataFromWeb(@PathParam("id") int id) throws MalformedURLException, IOException, InterruptedException {
        StringBuffer result = new StringBuffer();
        result.append("[");

        ArrayBlockingQueue<String> paths = new ArrayBlockingQueue(5);
        ArrayBlockingQueue<String> producedJson = new ArrayBlockingQueue(5);

        paths.add("https://swapi.co/api/people/" + id);
        paths.add("https://swapi.co/api/planets/" + id);
        paths.add("https://swapi.co/api/species/" + id);
        paths.add("https://swapi.co/api/vehicles/" + id);
        paths.add("https://swapi.co/api/starships/" + id);

        ExecutorService es = Executors.newCachedThreadPool();

        es.execute(new JsonProducer(paths, producedJson));
        es.execute(new JsonProducer(paths, producedJson));
        es.execute(new JsonProducer(paths, producedJson));
        es.execute(new JsonProducer(paths, producedJson));

        es.execute(new JsonConsumer(producedJson, result));

        es.shutdown();
        es.awaitTermination(5, TimeUnit.SECONDS);//?

        result.append("]");

        return result.toString();
    }

    @Path("username/{userName}")
    @DELETE
    //@RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userName") String userName) throws Exception {
        UserDTO userDTO = um.delete(userName);

        if (userDTO == null) {
            throw new Exception();
        }
        String userJSON = gson.toJson(userDTO, UserDTO.class);
        return Response.ok().entity(userJSON).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String json) throws Exception {
        User user = gson.fromJson(json, User.class);
        user.addRole(new Role("user"));
        user.hashPassword();
        
        UserDTO userDTO = um.create(user);

        if (userDTO == null) {
            throw new Exception();

        }

        String userJSON = gson.toJson(userDTO, UserDTO.class);

        return Response.status(Response.Status.CREATED).entity(userJSON).build();

    }

}
