package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.consulta.ConsultaCadastroDTO;
import com.ufes.prontuario.dto.consulta.ConsultaConverter;
import com.ufes.prontuario.dto.contato.ContatoCadastroDTO;
import com.ufes.prontuario.enums.TipoConsultaEnum;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Consulta;
import com.ufes.prontuario.model.Contato;
import com.ufes.prontuario.repository.ConsultaRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.specification.ConsultaSpecification;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConsultaService implements IBaseService<ConsultaCadastroDTO, Consulta> {

    private ConsultaRepository repository;
    private MedicoService medicoService;
    private PacienteService pacienteService;

    public Consulta findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Consulta", id));
    }

    public Page<Consulta> filter(Long idConsulta, String nomePaciente,
           String nomeMedico, LocalDate dataInicio, LocalDate dataFim, String tipoConsulta ,Pageable pageable) {
        var specification = this.prepareSpecification(idConsulta, nomePaciente, nomeMedico, dataInicio, dataFim, tipoConsulta);

        return this.repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    private Specification<Consulta> prepareSpecification(Long idConsulta, String nomePaciente,
           String nomeMedico, LocalDate dataInicio, LocalDate dataFim, String tipoConsulta) {
        final var specification = new ConsultaSpecification();

        return specification
                .and(specification.findByDataInicio(dataInicio))
                .and(specification.findByDataFim(dataFim))
                .and(specification.findById(idConsulta))
                .and(specification.findLikeByColumn("tipoConsulta", tipoConsulta))
                .and(specification.findLikeBySubColumn( "pessoa", "nome", nomePaciente))
                .and(specification.findLikeBySubColumn( "medico", "nome", nomeMedico));
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

    public Consulta update(Long id, ConsultaCadastroDTO consultaCadastroDTO) {
        return Optional.ofNullable(consultaCadastroDTO)
                .map(cDto -> validarUpdate(cDto, id))
                .map(consulta -> prepareUpdate(consulta ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    @Override
    public ConsultaCadastroDTO validarInsert(ConsultaCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public ConsultaCadastroDTO validarUpdate(ConsultaCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Consulta entity) {

    }

    @Override
    public Consulta prepareInsert(ConsultaCadastroDTO dtoCadastro) {
        var consulta = ConsultaConverter.toEntity(dtoCadastro);

        var medico = medicoService.findById(dtoCadastro.getIdMedico());
        var paciente = pacienteService.findById(dtoCadastro.getIdPaciente());

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        return consulta;
    }

    @Override
    public Consulta prepareUpdate(ConsultaCadastroDTO dtoCadastro, Long id) {
        var consulta = this.findById(id);

        consulta.setTipo(TipoConsultaEnum.valueOf(dtoCadastro.getTipo()));
        consulta.setMotivo(dtoCadastro.getMotivo());
        consulta.setPaciente(pacienteService.findById(dtoCadastro.getIdPaciente()));

        return consulta;
    }
}
