package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.consulta.ConsultaCadastroDTO;
import com.ufes.prontuario.model.Consulta;
import com.ufes.prontuario.repository.ConsultaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConsultaService implements IBaseService<ConsultaCadastroDTO, Consulta> {

    private ConsultaRepository repository;

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
