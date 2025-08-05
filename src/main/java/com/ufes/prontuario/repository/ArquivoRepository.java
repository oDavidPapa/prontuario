package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    @Query(value = """
    INSERT INTO arquivo (nome, arquivo, descricao, tipo, id_consulta)
    VALUES (:nome, :arquivo, :descricao, :tipo, :idConsulta)
    RETURNING id """, nativeQuery = true)
    Long insertArquivoNative(
            @Param("nome") String nome,
            @Param("arquivo") byte[] arquivo,
            @Param("descricao") String descricao,
            @Param("tipo") String tipo,
            @Param("idConsulta") Long idConsulta
    );
}
