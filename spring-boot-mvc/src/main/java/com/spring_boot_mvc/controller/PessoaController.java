package com.spring_boot_mvc.controller;
import com.spring_boot_mvc.model.Pessoa;
import com.spring_boot_mvc.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
