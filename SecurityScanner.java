import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Map;
import java.util.List;

public class SecurityScanner {

    
    public static String validateURL(String input) {
        if (input == null || input.isEmpty()) return null;
        input = input.trim();
        if (!input.startsWith("http://") && !input.startsWith("https://")) {
            input = "http://" + input;
        }
        try {
            new URL(input).toURI();
            return input;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- Analizues per sigurine e faqeve web ---");
        System.out.print("Shkruaj URL për analizen e plote: ");
        String rawInput = scanner.nextLine();

        // Thirrja e validimit para analizës
        String targetUrl = validateURL(rawInput);

        // Kontrolli nëse URL është e saktë
        if (targetUrl == null) {
            System.out.println("GABIM:  Vendos URL e sakte!!!!!");
            scanner.close();
            return;
        }

        try {
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            System.out.println("\n[ Raporti teknik i analizes ]");
            System.out.println(" ");

            //  analiza e enkriptimit dhe protokollit
            System.out.println("1. TRANSMISIONI I TË DHENAVE:");
            if (targetUrl.startsWith("https")) {
                System.out.println("   Statusi: i sigurt!! (TLS/SSL)");
                System.out.println("   Detaje Kanali i komunikimit eshte i enkriptuar , pra sulmet 'Sniffing' jane te pamundura");
            } else {
                System.out.println("   Statusi : ne rrezik!!!!!!");
                System.out.println("   Detaje: Ky protokoll nuk perdor enkriptim, cdo fjalekalim mund te kapet ne rrjet.");
            }

            // analiza e clickjacking
            System.out.println("\n2. INTEGRITETI I NDERFAQES :");
            String xfo = connection.getHeaderField("X-Frame-Options");
            if (xfo == null) {
                System.out.println("   Statusi : i thyeshem (CLICKJACKING)");
                System.out.println("   Detaje:  Mungesa e 'X-Frame-Options' lejon nje haker ta vendose kete faqe brenda nje <iframe>.");
                System.out.println("   RREZIK : Perdoruesi mund te klikoje nje buton te fshehur.");
            } else {
                System.out.println("   Statusi : i mbrojtur");
                System.out.println("   Detaje:  Serveri ka perdorur politiken " + xfo);
            }

            // analiza e serverit dhe informacionit
            System.out.println("\n3. INFORMACIONI I EKSPOZUAR I SERVERIT:");
            String server = connection.getHeaderField("Server");
            if (server != null) {
                System.out.println("   Status : Kujdes nga Info Leak");
                System.out.println("   Detaje Serveri tregon versionin e tij: " + server);
                System.out.println("   Rrezik: Exploits");
            } else {
                System.out.println("   Status: i sigurt");
                System.out.println("   Detaje: Serveri e ka fshehur identitetin ");
            }

            System.out.println("============================================");

        } catch (Exception e) {
            System.out.println("GABIM:  Vendos URL e sakte!!!!!");
        }
        scanner.close();
    }
}