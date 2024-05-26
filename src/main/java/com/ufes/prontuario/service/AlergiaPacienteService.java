package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.alergiapaciente.AlergiaPacienteCadastroDTO;
import com.ufes.prontuario.dto.alergiapaciente.AlergiaPacienteConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.AlergiaPaciente;
import com.ufes.prontuario.repository.AlergiaPacienteRepository;
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
public class AlergiaPacienteService implements IBaseService<AlergiaPacienteCadastroDTO, AlergiaPaciente> {

    private final AlergiaPacienteRepository repository;
    private final PacienteService pacienteService;

    public AlergiaPaciente findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("AlergiaPaciente", id));
    }

    public List<AlergiaPaciente> listar() {
        return this.repository.findAll();
    }


    public Page<AlergiaPaciente> filter(Long idPaciente, @Nullable Pageable pageable) {
        var specification = this.prepareSpecification(idPaciente);

        return repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    private Specification<AlergiaPaciente> prepareSpecification(Long idPaciente) {
        final var specification = new BaseSpecification<AlergiaPaciente>();

        return specification.and(specification.findBySubColumnId("paciente", "id", idPaciente));
    }

    public AlergiaPaciente inserir(AlergiaPacienteCadastroDTO alergiaPacienteCadastroDTO) {
        return Optional.ofNullable(alergiaPacienteCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public AlergiaPaciente update(Long id, AlergiaPacienteCadastroDTO alergiaPacienteCadastroDTO) {
        return Optional.ofNullable(alergiaPacienteCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(alergia -> prepareUpdate(alergia, id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var alergia = this.findById(id);

        Optional.ofNullable(alergia)
                .ifPresent(a -> {
                    this.validarDelete(a);
                    this.repository.delete(a);
                });
    }

    @Override
    public AlergiaPacienteCadastroDTO validarInsert(AlergiaPacienteCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public AlergiaPacienteCadastroDTO validarUpdate(AlergiaPacienteCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(AlergiaPaciente entity) {

    }

    @Override
    public AlergiaPaciente prepareInsert(AlergiaPacienteCadastroDTO dtoCadastro) {
        var alergiaPaciente = AlergiaPacienteConverter.toEntity(dtoCadastro);
        alergiaPaciente.setPaciente(pacienteService.findById(dtoCadastro.getIdPaciente()));

        return alergiaPaciente;
    }

    @Override
    public AlergiaPaciente prepareUpdate(AlergiaPacienteCadastroDTO dtoCadastro, Long id) {
        var alergiaPaciente = this.findById(id);
        alergiaPaciente.setDescricao(dtoCadastro.getDescricao());
        alergiaPaciente.setPaciente(this.pacienteService.findById(dtoCadastro.getIdPaciente()));

        return alergiaPaciente;
    }
}
