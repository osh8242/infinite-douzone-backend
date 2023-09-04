package com.douzone.rest.swsm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crawling")
@CrossOrigin(origins = "http://localhost:3000/")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/getData")
    public String ajaxCrawling(Model model) {
        System.out.println("Main Controller====");
        String image = mainService.webCrawling();
        System.out.println("service result: "+image);
        model.addAttribute("images", image.toString());

        return "jsonView";
    }


}



