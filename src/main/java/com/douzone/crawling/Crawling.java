//package com.douzone.crawling;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//
//public class Crawling {
//    static void test() throws IOException {
//
//        String WeatherURL = "https://weather.naver.com/today";
//
//        Document doc = Jsoup.connect(WeatherURL).get();
//
//        Elements elements = doc.select(".scroll_area .weather_table_wrap ._cnTimeTable");
//
//        String[] str = elements.text().split(" ");
//
//        System.out.println(str.length);
//        for (int i = 0; i < str.length; i++) {
//            System.out.println(str[i]);
//        }
//    }
//}
