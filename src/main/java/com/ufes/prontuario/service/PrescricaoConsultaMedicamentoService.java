package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.prescricaomedicamento.PrescricaoConsultaMedicamentoCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.PrescricaoConsultaMedicamento;
import com.ufes.prontuario.repository.PrescricaoConsultaMedicamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PrescricaoConsultaMedicamentoService implements IBaseService<PrescricaoConsultaMedicamentoCadastroDTO, PrescricaoConsultaMedicamento> {

    private final PrescricaoConsultaMedicamentoRepository repository;

    public PrescricaoConsultaMedicamento findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("PrescricaoConsultaMedicamento", id));
    }

    public List<PrescricaoConsultaMedicamento> listar() {
        return this.repository.findAll();
    }

    public PrescricaoConsultaMedicamento inserir(PrescricaoConsultaMedicamentoCadastroDTO prescricaoConsultaMedicamentoCadastroDTO) {
        return Optional.ofNullable(prescricaoConsultaMedicamentoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public PrescricaoConsultaMedicamento update(Long id, PrescricaoConsultaMedicamentoCadastroDTO prescricaoConsultaMedicamentoCadastroDTO) {
        return Optional.ofNullable(prescricaoConsultaMedicamentoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(prescricaoConsultaMedicamento -> prepareUpdate(prescricaoConsultaMedicamento ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var prescricaoConsultaMedicamento = this.findById(id);

        Optional.ofNullable(prescricaoConsultaMedicamento)
                .ifPresent(c -> {
                    this.validarDelete(c);
                    this.repository.delete(c);
                });
    }

    @Override
    public PrescricaoConsultaMedicamentoCadastroDTO validarInsert(PrescricaoConsultaMedicamentoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public PrescricaoConsultaMedicamentoCadastroDTO validarUpdate(PrescricaoConsultaMedicamentoCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(PrescricaoConsultaMedicamento entity) {

    }

    @Override
    public PrescricaoConsultaMedicamento prepareInsert(PrescricaoConsultaMedicamentoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public PrescricaoConsultaMedicamento prepareUpdate(PrescricaoConsultaMedicamentoCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
