package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.medicamento.MedicamentoCadastroDTO;
import com.ufes.prontuario.dto.medicamento.MedicamentoConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Medicamento;
import com.ufes.prontuario.repository.MedicamentoRepository;
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
public class MedicamentoService implements IBaseService<MedicamentoCadastroDTO, Medicamento> {

    private final MedicamentoRepository repository;

    public Medicamento findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Medicamento", id));
    }

    public List<Medicamento> listar() {
        return this.repository.findAll();
    }


    public Page<Medicamento> filter(Long id, String descricao, @Nullable Pageable pageable) {
        var specification = this.prepareSpecification(id, descricao);

        return repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    private Specification<Medicamento> prepareSpecification(Long id, String descricao) {
        final var specification = new BaseSpecification<Medicamento>();

        return specification
                .and(specification.findById(id))
                .and(specification.findLikeByColumn("descricao", descricao));
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
        return dtoCadastro;
    }

    @Override
    public MedicamentoCadastroDTO validarUpdate(MedicamentoCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Medicamento entity) {

    }

    @Override
    public Medicamento prepareInsert(MedicamentoCadastroDTO dtoCadastro) {
        return MedicamentoConverter.toEntity(dtoCadastro);
    }

    @Override
    public Medicamento prepareUpdate(MedicamentoCadastroDTO dtoCadastro, Long id) {
        var medicamento =this.findById(id);
        medicamento.setDescricao(dtoCadastro.getDescricao());

        return medicamento;
    }
}
