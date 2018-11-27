/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
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
 * @author Dradrach
 */
@Path("social")
public class SocialResource {

    @Context
    private UriInfo context;
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //authentificationContext;
    public static String oneTimeAuthentificationSocial = "";

//    static {
//        Thread thread = new SocialAuthentificationThread();
//        thread.start();
//    }
    public SocialResource() throws ProtocolException, IOException {
        socialAuthentication("CPHotels", "1234");
        
    }

    /**
     * Retrieves representation of an instance of rest.SocialResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getJson() {
    }

    /**
     * PUT method for updating or creating an instance of SocialResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    private void socialAuthentication(String name, String password) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL("http://81063aa7.ngrok.io/semester-3-exam-rest/api/services/authentication");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.addRequestProperty("Content-Type", "application/json;charset=UTF-8");
               con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            String json = "{\"name\":\"" + name + "\",\"password\":\"" + password + "\"}";
            wr.write(json.getBytes("UTF-8"));
        }
       
        Scanner scan = new Scanner(con.getInputStream());
        String json = "";
        while (scan.hasNext()) {
            json += scan.nextLine();
        }
        
        JsonObject je = new JsonParser().parse(json).getAsJsonObject();
        String token = je.get("token").getAsString();
        oneTimeAuthentificationSocial = token;
        
        
        
        scan.close();
    }
}


//class SocialAuthentificationThread extends Thread {
//
//    @Override
//    public void run(){
//        try {
//        URL url = new URL("");
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    con.setRequestMethod("GET");
//                    con.setRequestProperty("Accept", "application/json;charset=UTF-8");
//                    con.setRequestProperty("User-Agent", "server");
//                    Scanner scan = new Scanner(con.getInputStream());
//                    if (scan.hasNext()) {
//                        SocialResource.oneTimeAuthentificationSocial += scan.nextLine();
//                    }
//                    scan.close();
//        } catch(IOException exception){
//            System.out.println("Error when getting authentification from social");
//        }
//    }
//}
