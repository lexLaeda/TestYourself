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


    private String homeUrl;
    private String searchTag;
    @SneakyThrows
    public String parseHtml(){
        Connection connect = Jsoup.connect("https://ru.wikipedia.org/wiki/javascript" );
        Document document = connect.get();
        Element body = document.body();
        Elements elementsByTag = body.getElementsByTag("p");
        String sb = elementsByTag.stream().map(Element::text).collect(Collectors.joining());
        String[] split = sb.split(".");
        return sb;
    }

    public static void main(String[] args) {
        System.out.println(new WikiParser().parseHtml());
    }

}
