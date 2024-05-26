package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>, JpaSpecificationExecutor<Medicamento> {
}
