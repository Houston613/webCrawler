package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;

public class Parser {

    private final int depth;

    public Parser(int depth){
        this.depth = depth;
        //указываем количество ссылок, которые будут собраны в all
    }

    public LinkedHashSet<URL> htmlParser(LinkedHashSet<URL> needToParse, LinkedHashSet<URL> allURL) throws IndexOutOfBoundsException{

        LinkedHashSet<URL> urlsAdd = new LinkedHashSet<>();
        //ссылки собранные с текущей страницы
        try {
            for (URL url: needToParse) {
                Document doc = Jsoup.connect(String.valueOf(url)).get();
                //подключение к странице
                Elements links = doc.select("a[href]");
                //записываются все ссылки
                for (Element link : links) {
                    urlsAdd.add(new URL(link.attr("abs:href")));
                    //заменяет needToParse в след проходах

                    if (allURL.size()==depth)
                        return allURL;
                    //если достигли максимальной глубины, выходим из парсера

                    else
                        allURL.add(new URL(link.attr("abs:href")));
                }
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return htmlParser(urlsAdd, allURL);
        //если прошли все ссылки на странице, запускаем еще раз парсер по уже найденым стр
    }
}
