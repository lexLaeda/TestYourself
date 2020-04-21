package com.test.yourself.util;

import com.sun.jndi.toolkit.url.Uri;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WikiParser {

    private static final String WIKI = "https://ru.wikipedia.org/wiki/";
    @SneakyThrows
    public static String getDescription (String subject){

        Connection connect = Jsoup.connect(WIKI + subject );
        Document document = connect.get();
        Element body = document.body();
        Elements elementsByTag = body.getElementsByTag("p");

        return elementsByTag.stream()
                .map(Element::text)
                .collect(Collectors.joining());

    }


}
