package com.group3.ssdk;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SocialSDK
{

    private static HttpClient httpClient = HttpClientBuilder.create().build();
    private static Gson       gson       = SpecializedGson.create();

    private Service                             service;
    private String                              serviceToken;
    private String                              baseUrl;
    private String                              authCallback;
    private AuthRequestStore                    authRequestStore;
    private Map<String, Consumer<AuthResponse>> onResponses = new HashMap<>();

    public SocialSDK(String baseUrl, String authCallback, AuthRequestStore authRequestStore)
    {
        this.baseUrl = baseUrl;
        this.authCallback = authCallback;
        this.authRequestStore = authRequestStore;
    }

    /**
     * Creates a new service account.
     *
     * @param name     The name of the service account to create.
     * @param password The password of the service account to create.
     * @return The newly created service account. When the account cannot be created
     * {@code null} is returned instead.
     * @throws IOException
     */
    public Service register(String name, String password) throws IOException
    {
        HttpPost request = new HttpPost(baseUrl + "services/register");

        try {
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");

            JsonObject entity = new JsonObject();
            entity.addProperty("name", name);
            entity.addProperty("password", password);

            request.setEntity(new StringEntity(gson.toJson(entity)));
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != 201)
                return null;

            return this.service = gson.fromJson(body(response), ServiceRecord.class);
        } finally {
            request.releaseConnection();
        }
    }

    /**
     * Connects to the authentication server using the provided credentials.
     *
     * @param name     The name of the service account to connect with.
     * @param password The password of the service account to connect with.
     * @return {@code true} when the connection was successfully established, otherwise {@code false}.
     * @throws IOException
     */
    public boolean connect(String name, String password) throws IOException
    {

        HttpPost request = new HttpPost(baseUrl + "services/authenticate-service");

        try {
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");

            JsonObject entity = new JsonObject();
            entity.addProperty("name", name);
            entity.addProperty("password", password);

            request.setEntity(new StringEntity(gson.toJson(entity)));
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != 200)
                return false;

            ServiceAuthResponse serviceAuthResponse = gson.fromJson(body(response), ServiceAuthResponse.class);
            this.serviceToken = serviceAuthResponse.token;
            this.service = serviceAuthResponse.service;

            return true;
        } finally {
            request.releaseConnection();
        }
    }

    private class ServiceAuthResponse
    {
        public String        token;
        public ServiceRecord service;
    }

    /**
     * Creates a new permission template by querying the authentication server.
     *
     * @param name        The name of the template to create.
     * @param message     The message displayed to the user when prompted to accept the permission request.
     * @param permissions The permissions to request from users.
     * @return The newly created permission template object.
     * @throws IOException
     */
    public PermissionTemplate createPermissionTemplate(String name, String message, List<Permission> permissions) throws IOException
    {
        HttpPost request = new HttpPost(baseUrl + "services/templates");
        try {
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");
            request.addHeader("Authorization", prependBearer(this.serviceToken));

            JsonObject entity = new JsonObject();
            entity.addProperty("message", message);
            entity.addProperty("name", name);
            JsonArray permissionArray = new JsonArray();
            for (Permission permission : permissions)
                permissionArray.add(permission.name());
            entity.add("permissions", permissionArray);

            request.setEntity(new StringEntity(gson.toJson(entity)));
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != 201)
                return null;

            return gson.fromJson(body(response), PermissionTemplateRecord.class);
        } finally {
            request.releaseConnection();
        }
    }

    private String body(HttpResponse response) throws IOException
    {
        InputStream stream = null;

        try {
            stream = response.getEntity().getContent();
            return body(stream);
        } finally {
            if (stream != null)
                stream.close();
            EntityUtils.consume(response.getEntity());
        }
    }

    private String body(InputStream stream) throws IOException
    {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);
        }

        return builder.toString();
    }

    /**
     * Returns all the templates of the currently connected service.
     *
     * @return The permission templates.
     * @throws IOException
     */
    public List<PermissionTemplate> getTemplates() throws IOException
    {
        HttpGet request = new HttpGet(baseUrl + "services/templates");
        try {
            request.addHeader("Authorization", prependBearer(this.serviceToken));
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != 200)
                return null;

            Type listType = new TypeToken<ArrayList<PermissionTemplate>>()
            {
            }.getType();
            return gson.fromJson(body(response), listType);
        } finally {
            request.releaseConnection();
        }
    }

    /**
     * Returns a permission template by name.
     *
     * @param name The name of the template to return.
     * @return The template with the provided name, or {@code null} when no such
     * template exists.
     * @throws IOException
     */
    public PermissionTemplate getTemplateByName(String name) throws IOException
    {
        HttpGet request = new HttpGet(baseUrl + "services/templates/name/" + name);
        try {
            request.addHeader("Accept", "application/json");
            request.addHeader("Authorization", prependBearer(this.serviceToken));
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != 200)
                return null;

            return gson.fromJson(body(response), PermissionTemplateRecord.class);
        } finally {
            request.releaseConnection();
        }
    }

    /**
     * Returns authentication from some user.
     *
     * @param template   The template holding the permission to request from the user.
     * @param onResponse The action to perform when the user authenticates using the returned request.
     * @return The newly created auth request entity.
     * @throws IOException
     */
    public AuthRequest requestAuth(PermissionTemplate template, Consumer<AuthResponse> onResponse) throws IOException
    {
        HttpPost request = new HttpPost(baseUrl + "services/request-auth");
        try {
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");
            request.addHeader("Authorization", prependBearer(this.serviceToken));

            JsonObject entity = new JsonObject();
            entity.addProperty("callback", this.authCallback);
            if (template != null)
                entity.addProperty("template", template.getId());

            request.setEntity(new StringEntity(gson.toJson(entity)));
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != 201)
                return null;

            String      json    = body(response);
            AuthRequest created = gson.fromJson(json, AuthRequestRecord.class);
            onResponses.put(created.getId(), onResponse);
            authRequestStore.store(created);
            return created;
        } finally {
            request.releaseConnection();
        }
    }

    /**
     * Returns authentication from some user.
     *
     * @param onResponse The action to perform when the user authenticates using the returned request.
     * @return The newly created auth request entity.
     * @throws IOException
     */
    public AuthRequest requestAuth(Consumer<AuthResponse> onResponse) throws IOException
    {
        return requestAuth(null, onResponse);
    }

    /**
     * The method that should be notified when authentication callbacks are received by the server.
     *
     * @param json The response that was received.
     */
    public void onAuthResponse(String json)
    {
        AuthResponse response  = gson.fromJson(json, AuthResponseRecord.class);
        String       requestId = response.getRequest().getId();
        if (authRequestStore.has(requestId)) {
            Consumer<AuthResponse> onResponse = onResponses.get(requestId);
            if (onResponse != null) {
                onResponse.accept(response);
                authRequestStore.delete(requestId);
                onResponses.remove(requestId);
            }
        }
    }

    private String prependBearer(String token)
    {
        return "Bearer " + token;
    }
}
