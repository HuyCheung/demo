package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("error/{statusCode}.html")
    public String error(@PathVariable String statusCode) {
        return "error/" + statusCode;
    }

}