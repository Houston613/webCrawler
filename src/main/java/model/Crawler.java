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

        //сет который подается в парсер как изначальный
        LinkedHashSet<URL> needToParse = new LinkedHashSet<>();

        //добавляем первый URL в лист
        URL firstAdd = new URL(firstUrl);
        needToParse.add(firstAdd);

        //создаем экземпляр класса который парсит URL и сохраняет оттуда ссылки
        Parser parser = new Parser(numberOfUrl);

        return parser.htmlParser(needToParse,needToParse);
    }
}