package com.luv2code.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    //need controller method to show initial HTML form
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    //need controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    //need controller method to read form data and add data to the model
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model) {
        //read request parameter from the HTML form
        String theName = request.getParameter("studentName");

        //convert the data to all caps
        theName = theName.toUpperCase();

        //create message
        String result = "Yo! " + theName;

        //add message to the model
        model.addAttribute("message", result); //these are the name and value

        return "helloworld";
    }

    //request mapping supports any HTML requests normally
    //changing to get mapping makes it only get requests
    //with post it doesn't get appended to the url just sent with the html
    @PostMapping("/processFormVersionThree") //request param takes HTTP server request and binds it to theName param
    public String processFormVersionThree(@RequestParam("studentName") String theName, Model model) {
        //convert the data to all caps
        theName = theName.toUpperCase();

        //create message
        String result = "Hey My Friend from v3! " + theName;

        //add message to the model
        model.addAttribute("message", result); //these are the name and value

        return "helloworld";
    }
}
