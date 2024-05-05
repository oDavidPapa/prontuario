package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.PrescricaoConsultaMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescricaoConsultaMedicamentoRepository extends JpaRepository<PrescricaoConsultaMedicamento, Long> {
}
