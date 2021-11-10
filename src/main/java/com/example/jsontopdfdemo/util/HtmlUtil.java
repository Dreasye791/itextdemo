package com.example.jsontopdfdemo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author l
 * @Date 2021/11/10
 */
@Component
public class HtmlUtil {

    private static Document source;

    public static void setHtmlSource(File file) throws IOException {
        source = Jsoup.parse(file,"UTF-8");
    }


    public static void main(String[] args) throws IOException {
        setHtmlSource(new File("D:/a.html"));
        Elements elements = source.getElementsByTag("th");
        Element title = source.getElementById("title");
        Element customer = source.getElementById("customer");
        Element salesman =  source.getElementById("salesman");

        System.out.println(elements);
    }
}
