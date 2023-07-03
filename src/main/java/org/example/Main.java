import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

            //  Send the request synchronously and get the response //manejar la respuesta de una solicitud HTTP y asi procesar diferentes tipos de respuestas como texto, bytes o flujo de datos.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());//BodyHandlers 200ok

            // Create an ObjectMapper object
            ObjectMapper mapper = new ObjectMapper();

            //  Parse the response body as a JsonNode
            /* clase JsonNode es una clase de Jackson
            * que representa un nodo en un árbol JSOn*/
            JsonNode root = mapper.readTree(response.body());
            var event = root.path("england-and-wales").path("events").path(48);
            var August = event.path("title").asText();
            var date  = event.path("date"); //cast to day/month/year

            // Extract the bank holidays in England and Wales
//            JsonNode events = root.path("england-and-wales").path("events");
//            for (JsonNode event : events) {l
//                String title = event.path("title").asText();
//                String date = event.path("date").asText();
//                System.out.println(title + ": " + date);
//            }


//------------- formateando fechas con JsonNode-------------------------

            mapper.registerModule(new JavaTimeModule());

            // JSON string to parse
            String json = "{\"date\":"+date+"}";

            // Parse the JSON string into a JsonNode
            JsonNode datan = mapper.readTree(json);

            // Convert the value of the "date" field in the JsonNode into a LocalDate object
            LocalDate daten = mapper.convertValue(datan.get("date"), LocalDate.class);

            // Print the resulting LocalDate object
           // System.out.println("resulting LocalDate object "+daten);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateString = daten.format(formatter);
            //System.out.println("parse localDate to the desired format to display"+s);

//-----------------------------------------------------------------------


            System.out.println("HTTP response status code: "+response);
            //  System.out.println("Body "+root);
            System.out.println("Next bank holiday in UK is:  "+August+ " on "+dateString);



        } catch (Exception e) {
            System.out.println("URI is not valid");
            e.printStackTrace();
        }
    }



}
