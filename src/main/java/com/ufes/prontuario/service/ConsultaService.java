package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.consulta.ConsultaCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Consulta;
import com.ufes.prontuario.repository.ConsultaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConsultaService implements IBaseService<ConsultaCadastroDTO, Consulta> {

    private ConsultaRepository repository;

    public Consulta findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Consulta", id));
    }

    public List<Consulta> listar() {
        return this.repository.findAll();
    }

    public Consulta insert(ConsultaCadastroDTO consultaCadastroDTO) {
        return Optional.ofNullable(consultaCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    @Override
    public ConsultaCadastroDTO validarInsert(ConsultaCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public ConsultaCadastroDTO validarUpdate(ConsultaCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(Consulta entity) {

    }

    @Override
    public Consulta prepareInsert(ConsultaCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public Consulta prepareUpdate(ConsultaCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
