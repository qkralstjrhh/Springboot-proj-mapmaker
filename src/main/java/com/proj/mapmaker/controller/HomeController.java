package com.proj.mapmaker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class.getName());
    @GetMapping("/")
    public ModelAndView home(ModelAndView model, HttpSession session){
        model.setViewName("home");
        return model;
    }
    @GetMapping("/header")
    public String header(Model model) {

        return "/layout/header";
    }

    @GetMapping("/example")
    public ModelAndView example(ModelAndView mav){

        mav.setViewName("/example");

        return mav;
    }

}
