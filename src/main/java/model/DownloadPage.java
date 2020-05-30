package model;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class DownloadPage {

    public  void DownloadWebPage (URL webPage, int iter) {
        try {
            // Create URL object
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(webPage.openStream()));
            System.out.println("connect");

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter("Download"+iter+".html"));
            System.out.println("writer");

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write(System.lineSeparator());

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
