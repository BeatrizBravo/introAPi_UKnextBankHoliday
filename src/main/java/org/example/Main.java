import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Create a URL object with the desired API endpoint
            URL url = new URL("https://api.thecatapi.com/v1/images/search");

            // 2. Open a connection to the URL
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // 3. Set the request method (GET, POST, etc.)
            con.setRequestMethod("GET");

            // 4. Set the request headers
            con.setRequestProperty("x-api-key", "YOUR-API-KEY-HERE");

            // 5. Read the response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // 6. Print the response
            System.out.println(content.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
