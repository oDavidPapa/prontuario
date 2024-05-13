package com.ufes.prontuario.service;


import com.ufes.prontuario.dto.agendaconsulta.AgendaConsultaCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.AgendaConsulta;
import com.ufes.prontuario.repository.AgendaConsultaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendaConsultaService implements IBaseService<AgendaConsultaCadastroDTO, AgendaConsulta>{

    private final AgendaConsultaRepository repository;

    public AgendaConsulta findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("AgendaConulta", id));
    }

    public List<AgendaConsulta> listar() {
        return this.repository.findAll();
    }

    public AgendaConsulta inserir(AgendaConsultaCadastroDTO agendaConsultaCadastroDTO) {
        return Optional.ofNullable(agendaConsultaCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public AgendaConsulta update(Long id, AgendaConsultaCadastroDTO agendaConsultaCadastroDTO) {
        return Optional.ofNullable(agendaConsultaCadastroDTO)
                .map(acDto -> validarUpdate(acDto, id))
                .map(agendaConsulta -> prepareUpdate(agendaConsulta ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var agendaConsulta = this.findById(id);

        Optional.ofNullable(agendaConsulta)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }


    @Override
    public AgendaConsultaCadastroDTO validarInsert(AgendaConsultaCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public AgendaConsultaCadastroDTO validarUpdate(AgendaConsultaCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(AgendaConsulta entity) {

    }

    @Override
    public AgendaConsulta prepareInsert(AgendaConsultaCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public AgendaConsulta prepareUpdate(AgendaConsultaCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
