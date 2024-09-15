package com.ufes.prontuario.repository;

import com.ufes.prontuario.enums.TipoContatoEnum;
import com.ufes.prontuario.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>, JpaSpecificationExecutor<Contato> {

    Contato findByPessoaIdAndTipoContato(Long idPessoa, TipoContatoEnum tipoContato);
}
