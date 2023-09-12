package com.douzone.rest.swsm.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MainService {

    // 크롤링할 URL 주소
    private static final String CRAWLING_URL = "http://localhost:3000/";
    // 다운로드 받을 Dir 주소
    private static final String DIR = "D:\\1.download";
    // 파일명 갱신
    private static int FILE_NUM = 0;


    public String webCrawling() {
        try{
            System.out.println("Main Service ======");
            Connection conn = Jsoup.connect(CRAWLING_URL);
            System.out.println("conn paramger: "+conn);
            Document html = conn.get();
            // 수집할 Class 명

            System.out.println("==============================");
            System.out.println("html:\n\n\n\n\n\n\n");
            System.out.println(html);
            System.out.println("html end");
            System.out.println("================================\n\n\n\n\n\n\n");

            Elements imageUrlElements = html.getElementsByClass("thumbnail");

            System.out.println(imageUrlElements.toString());

            return imageUrlElements.toString();
        } catch(IOException ie){
            ie.printStackTrace();
            return "error";
        }
    }
}