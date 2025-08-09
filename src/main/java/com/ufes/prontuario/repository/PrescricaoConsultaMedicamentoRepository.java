package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.PrescricaoConsultaMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescricaoConsultaMedicamentoRepository extends JpaRepository<PrescricaoConsultaMedicamento, Long> {

    List<PrescricaoConsultaMedicamento> findAllByPrescricaoId(Long idPrescricao);

    List<PrescricaoConsultaMedicamento> findAllByPrescricaoConsultaId(Long idConsulta);

}
