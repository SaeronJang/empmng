package com.empmng.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class newWindowController {

    @GetMapping(value = "/")
    public String newWindowGet(){
        return "newWindow/newWindow";
    }
}
