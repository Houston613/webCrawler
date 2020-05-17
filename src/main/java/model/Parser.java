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
        //передается сет того, что нужно обработать
        // куда добавить
        try {
            LinkedHashSet<URL> urlsAdd = new LinkedHashSet<URL>();
            //найденные URL'ы будут записаны сюда и добавлены к all

            for (URL url: needToParse) {
                Document doc = Jsoup.connect(String.valueOf(url)).get();
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    urlsAdd.add(new URL(link.attr("abs:href")));
                    //заменяет needToParse в след проходах
                    if (allURL.size()==depth){
                        //если мы превысили лимит на ссылки - выбрасываем ошибку и завершаем программу
                        //иначе рекурсия не закончится пока не застопим руками или кнопкой
                        throw new IndexOutOfBoundsException("Поиск закончен");
                    }
                    allURL.add(new URL(link.attr("abs:href")));
                    //необходиомо метод, который будет после каждого вычисления апдейтить таблицу(а может и БД)
                }
            }
            htmlParser(urlsAdd,allURL);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return allURL;
    }
}
