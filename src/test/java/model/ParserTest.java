package model;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;

class ParserTest {

    @Test
    void htmlParser() throws MalformedURLException {
        Parser parser = new Parser(87);
        LinkedHashSet<URL> needToParse = new LinkedHashSet<URL>();
        needToParse.add(new URL("http://alexandr4784.narod.ru/index.html"));
        LinkedHashSet<URL> result = parser.htmlParser(needToParse,needToParse);
    }
}