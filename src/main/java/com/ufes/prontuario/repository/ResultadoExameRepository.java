package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.ResultadoExame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoExameRepository extends JpaRepository<ResultadoExame, Long> {

}
