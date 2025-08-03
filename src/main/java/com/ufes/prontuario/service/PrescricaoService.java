package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.prescricao.PrescricaoCadastroDTO;
import com.ufes.prontuario.dto.prescricao.PrescricaoConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Prescricao;
import com.ufes.prontuario.model.Tratamento;
import com.ufes.prontuario.repository.PrescricaoRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PrescricaoService implements IBaseService<PrescricaoCadastroDTO, Prescricao>{

    private final PrescricaoRepository repository;
    private final ConsultaService consultaService;

    public Prescricao findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Prescricao", id));
    }

    public List<Prescricao> listar() {
        return this.repository.findAll();
    }

    public Page<Prescricao> filter(Long idConsulta, Pageable pageable) {
        var specification = this.prepareSpecification(idConsulta);

        return this.repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    private Specification<Prescricao> prepareSpecification(Long idConsulta) {
        final var specification = new BaseSpecification<Prescricao>();

        return specification
                .and(specification.findBySubColumnId( "consulta", "id", idConsulta));

    }

    public Prescricao findByIdConsulta(Long idConsulta) {
        return this.repository.findByConsultaId(idConsulta);
    }

    public Prescricao inserir(PrescricaoCadastroDTO prescricaoCadastroDTO) {
        return Optional.ofNullable(prescricaoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Prescricao update(Long id, PrescricaoCadastroDTO prescricaoCadastroDTO) {
        return Optional.ofNullable(prescricaoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(prescricao -> prepareUpdate(prescricao ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var prescricao = this.findById(id);

        Optional.ofNullable(prescricao)
                .ifPresent(c -> {
                    this.validarDelete(c);
                    this.repository.delete(c);
                });
    }

    @Override
    public PrescricaoCadastroDTO validarInsert(PrescricaoCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public PrescricaoCadastroDTO validarUpdate(PrescricaoCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Prescricao entity) {

    }

    @Override
    public Prescricao prepareInsert(PrescricaoCadastroDTO dtoCadastro) {
        var prescricao = PrescricaoConverter.toEntity(dtoCadastro);
        prescricao.setConsulta(this.consultaService.findById(dtoCadastro.getIdConsulta()));
        return prescricao;
    }

    @Override
    public Prescricao prepareUpdate(PrescricaoCadastroDTO dtoCadastro, Long id) {
        var prescricao = this.findById(id);
        prescricao.setDescricao(dtoCadastro.getDescricao());
        return prescricao;
    }
}
