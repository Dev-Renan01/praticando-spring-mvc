package com.spring_boot_mvc.repository;

import com.spring_boot_mvc.model.Pessoa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

    @Query("select p from Pessoa p where p.nome like %?1% ")
    List<Pessoa> buscarPorNome(String nome);
}
