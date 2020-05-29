package model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;


public class Crawler {

    public Crawler(String firstUrl, int numberOfUrl) {
        this.firstUrl = firstUrl;
        this.numberOfUrl = numberOfUrl;
    }

    private final String firstUrl;
    private final int numberOfUrl;

    public LinkedHashSet<URL> crawl() throws MalformedURLException {

        LinkedHashSet<URL> needToParse = new LinkedHashSet<>();
        //сет который подается в парсер как изначальный
        URL firstAdd = new URL(firstUrl);
        needToParse.add(firstAdd);
        //добавляем первый URL в лист
        Parser parser = new Parser(numberOfUrl);
        //создаем экземпляр класса который парсит URL и сохраняет оттуда ссылки

        return parser.htmlParser(needToParse,needToParse);
    }
}