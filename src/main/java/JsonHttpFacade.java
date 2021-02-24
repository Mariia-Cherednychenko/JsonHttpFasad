import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonHttpFacade {
   HttpClient httpClient = HttpClient.newBuilder().build();
   ObjectMapper mapper = new ObjectMapper();


   public <T> T get(String s, Class<T> responseClass) {

       HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(s))
                .GET()
                .header("Authorization", "Bearer ")
               .header("Accept", "application/json")
                .build();

       try {
           HttpResponse<String> httpResponse =httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
           System.out.println(httpResponse.body());
          T response = mapper.readValue(httpResponse.body(), responseClass);
           return response;
    }
       catch (InterruptedException | IOException e) {
           e.printStackTrace();
       }


       return null;
    }


    public <T> T post(String s, CreateUserRequest req, Class<T> responseClass) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(s))
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(req)))
                .header("Authorization", "Bearer ")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<String> response =httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            T statusResponse = mapper.readValue(response.body(), responseClass);
            return statusResponse;
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public <T> T getAuthorized(String s, Class<T> responseClass,String token) {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(s))
                .GET()
                .header("Authorization", "Bearer " +token)
                .build();

        try {
            HttpResponse<String> httpResponse =httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            T response = mapper.readValue(httpResponse.body(), responseClass);
            return response;
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    public <T> T postAuthorized(String s, CreateUserRequest req, Class<T> responseClass,String token) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(s))
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(req)))
                .header("Authorization", "Bearer "+ token)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<String> response =httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            T statusResponse = mapper.readValue(response.body(), responseClass);
            return statusResponse;
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
