package model;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class DownloadPage {

    public  void DownloadWebPage (URL webPage) {
        try {
            // Create URL object
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(webPage.openStream()));

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter("Download.html"));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
            reader.close();
            writer.close();
        }
        catch (MalformedURLException mue) {
            System.out.println("Malformed URL Exception raised");
        }
        catch (IOException ie) {
            System.out.println("IOException raised");
        }
    }
}
