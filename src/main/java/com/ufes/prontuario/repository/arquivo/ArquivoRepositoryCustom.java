package com.ufes.prontuario.repository.arquivo;

import com.ufes.prontuario.model.Arquivo;

import java.util.List;
import java.util.Optional;

public interface ArquivoRepositoryCustom {

    Arquivo insertAndReturnArquivo(String nome, byte[] arquivo, String descricao, String tipo, Long idConsulta);

    List<Arquivo> findByIdConsulta(Long idConsulta);

    Optional<Arquivo> findById(Long id);

    boolean deleteById(Long id);

}
