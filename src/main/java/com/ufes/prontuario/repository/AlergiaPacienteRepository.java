package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.AlergiaPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlergiaPacienteRepository extends JpaRepository<AlergiaPaciente, Long>, JpaSpecificationExecutor<AlergiaPaciente> {

    List<AlergiaPaciente> findAllByPacienteId(Long idPaciente);
}
