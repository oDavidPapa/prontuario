package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Cid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidRepository extends JpaRepository<Cid, Long> {

    List<Cid> findAllByDiagnosticoId(Long idDiagnostico);

}
