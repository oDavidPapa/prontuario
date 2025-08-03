package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.tratamento.TratamentoCadastroDTO;
import com.ufes.prontuario.dto.tratamento.TratamentoConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Diagnostico;
import com.ufes.prontuario.model.Tratamento;
import com.ufes.prontuario.repository.TratamentoRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TratamentoService implements IBaseService<TratamentoCadastroDTO, Tratamento>{

    private final TratamentoRepository repository;
    private final ConsultaService consultaService;

    public Tratamento findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tratamento", id));
    }

    public List<Tratamento> listar() {
        return this.repository.findAll();
    }

    public Page<Tratamento> filter(Long idConsulta, @Nullable Pageable pageable) {
        var specification = this.prepareSpecification(idConsulta);

        return repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    public Tratamento findByIdConsulta(Long idConsulta) {
        return this.repository.findByConsultaId(idConsulta);
    }

    private Specification<Tratamento> prepareSpecification(Long idConsulta) {
        final var specification = new BaseSpecification<Tratamento>();
        return specification.and(specification.findBySubColumnId("consulta", "id", idConsulta));
    }

    public Tratamento inserir(TratamentoCadastroDTO tratamentoCadastroDTO) {
        return Optional.ofNullable(tratamentoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Tratamento update(Long id, TratamentoCadastroDTO tratamentoCadastroDTO) {
        return Optional.ofNullable(tratamentoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(tratamento -> prepareUpdate(tratamento ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var tratamento = this.findById(id);

        Optional.ofNullable(tratamento)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }

    @Override
    public TratamentoCadastroDTO validarInsert(TratamentoCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public TratamentoCadastroDTO validarUpdate(TratamentoCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Tratamento entity) {

    }

    @Override
    public Tratamento prepareInsert(TratamentoCadastroDTO dtoCadastro) {
        var tratamento = TratamentoConverter.toEntity(dtoCadastro);
        tratamento.setConsulta(this.consultaService.findById(dtoCadastro.getIdConsulta()));

        return tratamento;
    }

    @Override
    public Tratamento prepareUpdate(TratamentoCadastroDTO dtoCadastro, Long id) {
        var tratamento = this.findById(id);
        tratamento.setTratamento(dtoCadastro.getTratamento());

        return tratamento;
    }
}
