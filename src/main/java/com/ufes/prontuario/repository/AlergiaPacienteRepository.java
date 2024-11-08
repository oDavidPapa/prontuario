package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.AlergiaPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergiaPacienteRepository extends JpaRepository<AlergiaPaciente, Long>, JpaSpecificationExecutor<AlergiaPaciente> {

}
