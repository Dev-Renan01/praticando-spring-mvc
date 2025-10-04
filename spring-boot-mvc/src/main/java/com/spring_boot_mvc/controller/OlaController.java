package com.spring_boot_mvc.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OlaController {

    @RequestMapping (value = "/")
    public String index(){
        return "index";
    }
}
