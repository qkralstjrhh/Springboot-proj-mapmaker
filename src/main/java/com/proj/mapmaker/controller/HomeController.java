package com.proj.mapmaker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class.getName());
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("data", "데이터@@");
        return "home";
    }
    @GetMapping("/header")
    public String header(Model model) {

        return "/layout/header";
    }

}
