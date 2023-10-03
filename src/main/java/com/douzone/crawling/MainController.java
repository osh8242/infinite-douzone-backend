package com.douzone.crawling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/crawling")
    public String ajaxCrawling(Model model){
        String image = mainService.webCrawling();

        model.addAttribute("images", image.toString());

        return "jsonView";
    }






}