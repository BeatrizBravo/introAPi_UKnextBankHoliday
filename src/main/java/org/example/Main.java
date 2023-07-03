import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        try {
            //  Create an HttpClient object
            var client = HttpClient.newHttpClient();

            //  Create an HttpRequest
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.gov.uk/bank-holidays.json"))
                    .GET()
                    .build();

            //  Send the request synchronously and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Create an ObjectMapper object
            ObjectMapper mapper = new ObjectMapper();

            //  Parse the response body as a JsonNode
            JsonNode root = mapper.readTree(response.body());

            // Extract the bank holidays in England and Wales
            JsonNode events = root.path("england-and-wales").path("events");
            for (JsonNode event : events) {
                String title = event.path("title").asText();
                String date = event.path("date").asText();
                System.out.println(title + ": " + date);
            }

        } catch (Exception e) {
            System.out.println("URI is not valid");
            e.printStackTrace();
        }
    }
}
