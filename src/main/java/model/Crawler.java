package model;

import java.net.URL;
import java.util.LinkedHashSet;

public class Crawler {

    private final URL startUrl;
    private final int depth;
    //private final boolean needDownload;
    //первая ссылка полученная от пользователя.

    public Crawler(URL startUrl, int depth) {
        this.startUrl = startUrl;
        //стартовый url
        this.depth = depth;
        //количество ссылок
        //this.needDownload = needDownload;
    }
    //в графике заблочить возможность не ввести startURL

    public LinkedHashSet<URL> crawl() {

        LinkedHashSet<URL> needToParse = new LinkedHashSet<URL>();
        //сет который подается в парсер как изначальный

        needToParse.add(startUrl);
        //добавляем первый URL в лист

        Parser parser = new Parser(depth);
        //создаем экземпляр класса который парсит URL и сохраняет оттуда ссылки

        LinkedHashSet result = parser.htmlParser(needToParse,needToParse);
        //список всех ссылок
    return result;
    }
}