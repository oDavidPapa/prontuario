package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.agenda.AgendaCadastroDTO;
import com.ufes.prontuario.dto.agenda.AgendaConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Agenda;
import com.ufes.prontuario.repository.AgendaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2
public class AgendaService implements IBaseService<AgendaCadastroDTO, Agenda> {

    private final AgendaRepository repository;

    public Agenda findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agenda", id));
    }

    public List<Agenda> listar() {
        return this.repository.findAll();
    }

    public Agenda inserir(AgendaCadastroDTO agendaCadastroDTO) {
        log.info("Inserir agenda...");
        return Optional.ofNullable(agendaCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public void save(Agenda agenda) {
        this.repository.save(agenda);
    }

    public Agenda update(Long id, AgendaCadastroDTO agendaCadastroDTO) {
        log.info("Update agenda id={}", id);
        return Optional.ofNullable(agendaCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(agenda -> prepareUpdate(agenda ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        log.info("Delete agenda id={}", id);
        var agenda = this.findById(id);

        Optional.ofNullable(agenda)
                .ifPresent(a -> {
                    this.validarDelete(a);
                    this.repository.delete(a);
                });
    }

    @Override
    public AgendaCadastroDTO validarInsert(AgendaCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public AgendaCadastroDTO validarUpdate(AgendaCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Agenda entity) {

    }

    @Override
    public Agenda prepareInsert(AgendaCadastroDTO dtoCadastro) {
        return AgendaConverter.toEntity(dtoCadastro);
    }

    @Override
    public Agenda prepareUpdate(AgendaCadastroDTO dtoCadastro, Long id) {
        var agenda = this.findById(id);

        agenda.setDataAgendamento(dtoCadastro.getDataAgendamento());
        agenda.setDescricao(dtoCadastro.getDescricao());

        return agenda;
    }
}
