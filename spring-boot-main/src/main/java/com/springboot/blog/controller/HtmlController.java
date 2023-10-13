package com.springboot.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HtmlController {
    Logger logger = LoggerFactory.getLogger(HtmlController.class);
    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String loginPost(){
        logger.info("Login");
        return "redirect:/index";
    }
    @GetMapping("/new-post")
    public String newPost(){
        return "post";
    }
}
