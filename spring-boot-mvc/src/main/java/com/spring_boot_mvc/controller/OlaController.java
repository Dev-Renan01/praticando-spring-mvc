package com.spring_boot_mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OlaController {

    @RequestMapping (value = "/boasvindas/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String ola(@PathVariable String nome){

        return "Ola, " + nome + "seja bem vindo ao meu projeto!";
    }
}
