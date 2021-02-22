import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonHttpFacade {
   HttpClient httpClient = HttpClient.newBuilder().build();
   ObjectMapper mapper = new ObjectMapper();


   public UserResponse get(String s, Class<UserResponse> userResponseClass) {

       HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(s))
                .GET()
                .header("Authorization", "Bearer ")
                .build();

       try {
           HttpResponse<String> response =httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
           UserResponse userResponse = mapper.readValue(response.body(), userResponseClass);
           return userResponse;
    }
       catch (InterruptedException | IOException e) {
           e.printStackTrace();
       }


       return null;
    }


    public StatusResponse post(String s, CreateUserRequest req, Class<StatusResponse> statusResponseClass) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(s))
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(req)))
                .header("Authorization", "Bearer ")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<String> response =httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            StatusResponse statusResponse = mapper.readValue(response.body(), statusResponseClass);
            return statusResponse;
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
