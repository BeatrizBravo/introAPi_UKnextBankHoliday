import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Create an HttpClient object
            HttpClient client = HttpClient.newHttpClient();

            // 2. Create an HttpRequest object with the desired API endpoint and request method
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.gov.uk/bank-holidays.json"))
                    //.method("GET", HttpRequest.BodyPublishers.noBody()) //method is used as a shorthand to set the request method to GET.
                    .GET() //method is used as a shorthand to set the request method to GET.
                    .build();


            // 3. Send the request synchronously and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Print the response
            System.out.println(response.body());

        } catch (Exception e) {

            System.out.println("URI is not valid");
            e.printStackTrace();
        }
    }
}
