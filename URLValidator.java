import java.net.URL;
import java.net.MalformedURLException;

public class URLValidator {
    
   
    public static String sanitize(String input) {
        if (input == null || input.isEmpty()) return "";
        input = input.trim();
        if (!input.startsWith("http://") && !input.startsWith("https://")) {
            input = "http://" + input;
        }
        return input;
    }

    
    public static boolean isValid(String urlString) {
        try {
            new URL(urlString).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}