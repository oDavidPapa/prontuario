package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.resultadoexame.ResultadoExameCadastroDTO;
import com.ufes.prontuario.dto.resultadoexame.ResultadoExameConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.ResultadoExame;
import com.ufes.prontuario.repository.ResultadoExameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ResultadoExameService implements IBaseService<ResultadoExameCadastroDTO, ResultadoExame>{

    private final ResultadoExameRepository repository;
    private final ExameService exameService;
    private final ArquivoService arquivoService;

    public ResultadoExame findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("ResultadoExame", id));
    }

    public List<ResultadoExame> listar() {
        return this.repository.findAll();
    }

    public ResultadoExame inserir(ResultadoExameCadastroDTO resultadoExameCadastroDTO) {
        return Optional.ofNullable(resultadoExameCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public ResultadoExame update(Long id, ResultadoExameCadastroDTO resultadoExameCadastroDTO) {
        return Optional.ofNullable(resultadoExameCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(resultadoExame -> prepareUpdate(resultadoExame ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var resultadoExame = this.findById(id);

        Optional.ofNullable(resultadoExame)
                .ifPresent(re -> {
                    this.validarDelete(re);
                    this.repository.delete(re);
                });
    }

    @Override
    public ResultadoExameCadastroDTO validarInsert(ResultadoExameCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public ResultadoExameCadastroDTO validarUpdate(ResultadoExameCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(ResultadoExame entity) {

    }

    @Override
    public ResultadoExame prepareInsert(ResultadoExameCadastroDTO dtoCadastro) {

        var resultadoExame = ResultadoExameConverter.toEntity(dtoCadastro);
        resultadoExame.setArquivo(this.arquivoService.findById(dtoCadastro.getIdArquivo()));
        resultadoExame.setExame(this.exameService.findById(dtoCadastro.getIdExame()));

        return resultadoExame;
    }

    @Override
    public ResultadoExame prepareUpdate(ResultadoExameCadastroDTO dtoCadastro, Long id) {
        return this.findById(id);
    }
}
