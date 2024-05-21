package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>, JpaSpecificationExecutor<Medico> {
}
