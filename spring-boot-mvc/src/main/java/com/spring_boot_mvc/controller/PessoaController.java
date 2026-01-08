package com.spring_boot_mvc.controller;
import com.spring_boot_mvc.model.Pessoa;
import com.spring_boot_mvc.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;


    @RequestMapping (value = "/cadastropessoa", method = RequestMethod.GET)
    public ModelAndView inicio(){ // ModelAndView é uma classe do Spring MVC que une o Model e a View

        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");

        andView.addObject("pessoaobj", new Pessoa());
        return andView;
    }

    @RequestMapping(value = "/salvarpessoa", method = RequestMethod.POST)
    public ModelAndView salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);

        ModelAndView modelAndView = new ModelAndView("cadastro/poscadastro");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();

        modelAndView.addObject("pessoas", pessoasIt);     // Envia a lista de pessoas para a view
        modelAndView.addObject("pessoaobj", new Pessoa());    // Envia um novo objeto Pessoa vazio para limpar o formulário

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

    @PostMapping(value = "/editarpessoa/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa){

        Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
        andView.addObject("pessoaobj", pessoa.get()); // Envia a pessoa encontrada para o formulário

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

    @GetMapping(value = "/pesquisarpessoa")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa){

        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
        andView.addObject("pessoas", pessoaRepository.buscarPorNome(nomepesquisa));
        andView.addObject("pessoaobj", new Pessoa());

        return andView;
    }
}