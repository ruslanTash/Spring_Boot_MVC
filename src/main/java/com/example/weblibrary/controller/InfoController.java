package com.example.weblibrary.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("GET")
public class InfoController {

    @GetMapping("appInfo")
    public String appInfo() {
        return app;

    }
    @Value("${app.env}")
    private String app;

}
