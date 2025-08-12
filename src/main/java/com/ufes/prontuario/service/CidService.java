package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.doenca.CidCadastroDTO;
import com.ufes.prontuario.dto.doenca.CidConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Cid;
import com.ufes.prontuario.model.Diagnostico;
import com.ufes.prontuario.repository.CidRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2
public class CidService implements IBaseService<CidCadastroDTO, Cid>{

    private final CidRepository repository;
    private final DiagnosticoService diagnosticService;

    public Cid findById(Long id) {
        log.info("Buscando CID id={}", id);
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cid", id));
    }

    public List<Cid> listar() {
        return this.repository.findAll();
    }

    public List<Cid> findByDiagnostico(Long idDiagnostico) {
        log.info("Buscando CID by Diagnostico id={}", idDiagnostico);
        return this.repository.findAllByDiagnosticoId(idDiagnostico);
    }

    public Cid inserir(CidCadastroDTO cidCadastroDTO) {
        log.info("Inserindo CID...");
        return Optional.ofNullable(cidCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Cid update(Long id, CidCadastroDTO cidCadastroDTO) {
        log.info("Update CID id={}", id);

        return Optional.ofNullable(cidCadastroDTO)
                .map(dDto -> validarUpdate(dDto, id))
                .map(cid -> prepareUpdate(cid ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        log.info("Delete CID id={}", id);
        var cid = this.findById(id);

        Optional.ofNullable(cid)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }

    @Override
    public CidCadastroDTO validarInsert(CidCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public CidCadastroDTO validarUpdate(CidCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Cid entity) {

    }

    @Override
    public Cid prepareInsert(CidCadastroDTO dtoCadastro) {
        var cid = CidConverter.toEntity(dtoCadastro);
        cid.setDiagnostico(diagnosticService.findById(dtoCadastro.getIdDiagnostico()));
       return cid;
    }

    @Override
    public Cid prepareUpdate(CidCadastroDTO dtoCadastro, Long id) {
        var cid = this.findById(id);
        cid.setDescricao(dtoCadastro.getDescricao());
        cid.setCodigo(dtoCadastro.getCodigo());

        return cid;
    }
}

