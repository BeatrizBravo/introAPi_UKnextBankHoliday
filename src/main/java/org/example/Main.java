import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static class EventData{
        public String title;
        public String date;
        public String notes;
        public String bunting;
    }

    public static class CountryData{
      public String division;
      public List<EventData>events;
  }

    public static class BankHolidayData{

       @JsonProperty("england-and-wales")
       public CountryData englandAnWales;

       @JsonProperty("norther-ireland")
       public CountryData northernIreland;
       public CountryData scontland;
   }

    public static void main(String[] args) {
        try {

            HttpClient client = HttpClient.newHttpClient();


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.gov.uk/bank-holidays.json"))
                    //.method("GET", HttpRequest.BodyPublishers.noBody()) //method is used as a shorthand to set the request method to GET.
                    .GET() //method is used as a shorthand to set the request method to GET.
                    .build();


            // 3. Send the request synchronously and get the response
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            var body = response.body();
            var objectMapper = new ObjectMapper();
            var events = objectMapper.readTree(body).get("england-and-wales").get("events");
            for (var event:events){
                var date = event.get("date").asText();
                System.out.println("date" + date);
                }
            System.out.println("events"+events);

        } catch (Exception e) {

            System.out.println("URI is not valid");
            e.printStackTrace();
        }
    }
}
