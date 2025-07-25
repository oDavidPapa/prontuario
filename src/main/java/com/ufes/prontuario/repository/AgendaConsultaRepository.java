package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.AgendaConsulta;
import com.ufes.prontuario.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaConsultaRepository extends JpaRepository<AgendaConsulta, Long>, JpaSpecificationExecutor<AgendaConsulta> {
}
