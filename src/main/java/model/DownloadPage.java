package model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class DownloadPage {
    private static final Logger logger = LogManager.getLogger();
    public  void DownloadWebPage (URL webPage, int iter) {
        try {
            // Create URL object
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(webPage.openStream()));
            logger.info("reader - success");
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter("Download"+ iter +".html"));
            logger.info("writer - success");

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
