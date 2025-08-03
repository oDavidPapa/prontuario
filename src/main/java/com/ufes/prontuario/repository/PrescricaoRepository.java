package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Prescricao;
import com.ufes.prontuario.model.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescricaoRepository extends JpaRepository<Prescricao, Long>, JpaSpecificationExecutor<Prescricao> {

    Prescricao findByConsultaId(Long idConsulta);

}
