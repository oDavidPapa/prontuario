package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Diagnostico;
import com.ufes.prontuario.model.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamentoRepository extends JpaRepository<Tratamento, Long>, JpaSpecificationExecutor<Tratamento> {

    Tratamento findByConsultaId(Long idConsulta);

}
