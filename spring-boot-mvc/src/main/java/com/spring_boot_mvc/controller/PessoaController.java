package com.spring_boot_mvc.controller;
import com.spring_boot_mvc.model.Pessoa;
import com.spring_boot_mvc.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;


    @RequestMapping (value = "/cadastropessoa", method = RequestMethod.GET)
    public String inicio(){
        return "cadastro/cadastropessoa";
    }

    @RequestMapping(value = "/salvarpessoa", method = RequestMethod.POST)
    public String salvar(Pessoa pessoa){

        pessoaRepository.save(pessoa);
        return "cadastro/poscadastro";
    }

    @RequestMapping(value = "/listarpessoas", method = RequestMethod.GET)
    public ModelAndView pessoas(){

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();

        modelAndView.addObject("pessoas", pessoasIt);

        return modelAndView;
    }

}