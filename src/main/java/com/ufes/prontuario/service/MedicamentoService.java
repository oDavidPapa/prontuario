package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.medicamento.MedicamentoCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Medicamento;
import com.ufes.prontuario.repository.MedicamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MedicamentoService implements IBaseService<MedicamentoCadastroDTO, Medicamento> {

    private final MedicamentoRepository repository;

    public Medicamento findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Medicamento", id));
    }

    public List<Medicamento> listar() {
        return this.repository.findAll();
    }

    public Medicamento inserir(MedicamentoCadastroDTO medicamentoCadastroDTO) {
        return Optional.ofNullable(medicamentoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Medicamento update(Long id, MedicamentoCadastroDTO medicamentoCadastroDTO) {
        return Optional.ofNullable(medicamentoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(medicamento -> prepareUpdate(medicamento ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var medicamento = this.findById(id);

        Optional.ofNullable(medicamento)
                .ifPresent(c -> {
                    this.validarDelete(c);
                    this.repository.delete(c);
                });
    }

    @Override
    public MedicamentoCadastroDTO validarInsert(MedicamentoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public MedicamentoCadastroDTO validarUpdate(MedicamentoCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(Medicamento entity) {

    }

    @Override
    public Medicamento prepareInsert(MedicamentoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public Medicamento prepareUpdate(MedicamentoCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
