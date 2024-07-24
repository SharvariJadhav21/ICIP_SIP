import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SearchExample {
    public static void main(String[] args) {
        try {
            String query = "Java programming";
            String html = fetchHtml(query);
            parseResults(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fetchHtml(String query) throws IOException {
        String encodedQuery = URLEncoder.encode(query, "UTF-8");
        String urlString = "https://www.google.com/search?q=" + encodedQuery;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        return response.toString();
    }

    private static void parseResults(String html) {
        // Simple HTML parsing for demonstration purposes
        // This is a very basic example and may not handle all cases

        // Find start and end of search result entries
        String startTag = "<h3 class=\"";
        String endTag = "</h3>";

        int startIndex = 0;
        while ((startIndex = html.indexOf(startTag, startIndex)) != -1) {
            int endIndex = html.indexOf(endTag, startIndex);
            if (endIndex == -1) break;

            String resultTitle = html.substring(startIndex + startTag.length(), endIndex).trim();
            resultTitle = resultTitle.replaceAll("<[^>]+>", ""); // Remove HTML tags

            // Print the title
            System.out.println("Title: " + resultTitle);
            startIndex = endIndex + endTag.length();
        }
    }
}
