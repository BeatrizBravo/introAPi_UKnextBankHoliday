import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Create an HttpClient object
            HttpClient client = HttpClient.newHttpClient();

            // 2. Create an HttpRequest object with the desired API endpoint
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.thecatapi.com/v1/images/search"))
                    .header("x-api-key", "YOUR-API-KEY-HERE")
                    .build();

            // 3. Send the request synchronously and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Print the response
            System.out.println(response.body());
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
