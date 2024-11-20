package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Endereco findByPessoaId(Long id);
}
