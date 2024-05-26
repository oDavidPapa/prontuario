package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.exame.ExameCadastroDTO;
import com.ufes.prontuario.dto.exame.ExameConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Exame;
import com.ufes.prontuario.repository.ExameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ExameService implements IBaseService<ExameCadastroDTO, Exame>{

    private final ExameRepository repository;
    private final ConsultaService consultaService;

    public Exame findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Exame", id));
    }

    public List<Exame> listar() {
        return this.repository.findAll();
    }

    public Exame inserir(ExameCadastroDTO exameCadastroDTO) {
        return Optional.ofNullable(exameCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Exame update(Long id, ExameCadastroDTO exameCadastroDTO) {
        return Optional.ofNullable(exameCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(exame -> prepareUpdate(exame ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var exame = this.findById(id);

        Optional.ofNullable(exame)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }

    @Override
    public ExameCadastroDTO validarInsert(ExameCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public ExameCadastroDTO validarUpdate(ExameCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Exame entity) {

    }

    @Override
    public Exame prepareInsert(ExameCadastroDTO dtoCadastro) {
        var exame = ExameConverter.toEntity(dtoCadastro);
        exame.setConsulta(this.consultaService.findById(dtoCadastro.getIdConsulta()));

        return exame;
    }

    @Override
    public Exame prepareUpdate(ExameCadastroDTO dtoCadastro, Long id) {
        var exame = this.findById(id);
        exame.setDescricao(dtoCadastro.getDescricao());

        return exame;
    }
}
