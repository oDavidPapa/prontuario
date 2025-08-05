package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

    List<Exame> findAllByConsultaId(Long consultaId);
}
