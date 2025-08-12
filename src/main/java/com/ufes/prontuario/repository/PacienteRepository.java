package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.AlergiaPaciente;
import com.ufes.prontuario.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>, JpaSpecificationExecutor<Paciente> {

    @Query(value = "SELECT p.* " +
            "FROM consulta c " +
            "JOIN paciente p ON c.id_paciente = p.id " +
            "WHERE c.id = :idConsulta",
            nativeQuery = true)
    Paciente findByConsultaId(@Param("idConsulta") Long idConsulta);
}
