package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;

public class Parser {
    private static final Logger logger = LogManager.getLogger();
    private final int depth;

    //указываем количество ссылок, которые будут собраны в all
    public Parser(int depth){
        this.depth = depth;
    }

    public LinkedHashSet<URL> htmlParser(LinkedHashSet<URL> needToParse, LinkedHashSet<URL> allURL) throws IndexOutOfBoundsException{

        //ссылки собранные с текущей страницы
        //кажлдый раз новые
        LinkedHashSet<URL> urlsAdd = new LinkedHashSet<>();

        try {
            for (URL url: needToParse) {

                //подключение к странице
                Document doc = Jsoup.connect(String.valueOf(url)).get();
                logger.info("jsoup connect - success");

                //записываются все ссылки
                Elements links = doc.select("a[href]");

                for (Element link : links) {
                    //заменяет needToParse в след проходах рекурсии
                    urlsAdd.add(new URL(link.attr("abs:href")));

                    //если достигли максимальной глубины, выходим из парсера
                    if (allURL.size()==depth)
                        return allURL;

                    else
                        //парсим дальше
                        allURL.add(new URL(link.attr("abs:href")));
                }
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

        //если прошли все ссылки на странице, запускаем еще раз парсер по уже найденым стр
        return htmlParser(urlsAdd, allURL);
    }
}
