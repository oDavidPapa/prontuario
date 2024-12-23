package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.DoencaDiagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoencaDiagnosticoRepository extends JpaRepository<DoencaDiagnostico, Long> {
}
