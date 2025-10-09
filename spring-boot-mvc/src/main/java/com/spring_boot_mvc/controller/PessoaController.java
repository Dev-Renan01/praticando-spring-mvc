package com.spring_boot_mvc.controller;
import com.spring_boot_mvc.model.Pessoa;
import com.spring_boot_mvc.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.Optional;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;


    @RequestMapping (value = "/cadastropessoa", method = RequestMethod.GET)
    public ModelAndView inicio(){

        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
        andView.addObject("pessoaobj", new Pessoa());
        return andView;
    }

    @RequestMapping(value = "/{path:.*}/salvarpessoa", method = RequestMethod.POST)
    public ModelAndView salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();

        modelAndView.addObject("pessoas", pessoasIt);
        modelAndView.addObject("pessoaobj", new Pessoa());

        return modelAndView;
    }

    @RequestMapping(value = "/listarpessoas", method = RequestMethod.GET)
    public ModelAndView pessoas(){

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();

        modelAndView.addObject("pessoas", pessoasIt);
        modelAndView.addObject("pessoaobj", new Pessoa());

        return modelAndView;
    }

    @GetMapping(value = "/editarpessoa/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa){

        Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
        andView.addObject("pessoaobj", pessoa.get());
        return andView;
    }

    @GetMapping("/deletarpessoa/{idpessoa}")
    public ModelAndView deletarpessoa(@PathVariable(name = "idpessoa") Long idpessoa){

        pessoaRepository.deleteById(idpessoa);

        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
        andView.addObject("pessoas", pessoaRepository.findAll());
        andView.addObject("pessoaobj", new Pessoa());
        return andView;
    }
}