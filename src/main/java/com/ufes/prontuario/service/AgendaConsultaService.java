package com.ufes.prontuario.service;


import com.ufes.prontuario.dto.agenda.AgendaCadastroDTO;
import com.ufes.prontuario.dto.agendaconsulta.AgendaConsultaCadastroDTO;
import com.ufes.prontuario.dto.agendaconsulta.AgendaConsultaConverter;
import com.ufes.prontuario.dto.consulta.ConsultaCadastroDTO;
import com.ufes.prontuario.enums.TipoConsultaEnum;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.AgendaConsulta;
import com.ufes.prontuario.repository.AgendaConsultaRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.util.CodeUtils;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendaConsultaService implements IBaseService<AgendaConsultaCadastroDTO, AgendaConsulta> {

    private final AgendaConsultaRepository repository;

    private MedicoService medicoService;
    private PacienteService pacienteService;
    private AgendaService agendaService;
    private ConsultaService consultaService;

    @Autowired
    public void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Autowired
    public void setMedicoService(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Autowired
    public void setAgendaService(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @Autowired
    public void setConsultaService(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    public AgendaConsulta findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("AgendaConulta", id));
    }

    public List<AgendaConsulta> listar() {
        return this.repository.findAll();
    }

    public Page<AgendaConsulta> filter(String nomePaciente,
                                       String nomeMedico, LocalDate dataInicio, LocalDate dataFim, String cpfPaciente, Pageable pageable) {
        var specification = this.prepareSpecification(nomePaciente, nomeMedico, dataInicio, dataFim, cpfPaciente);

        return this.repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    private Specification<AgendaConsulta> prepareSpecification(String nomePaciente, String nomeMedico, LocalDate dataInicio, LocalDate dataFim, String cpfPaciente) {
        final var specification = new BaseSpecification<AgendaConsulta>();

        return specification
                .and(specification.subColumnfindByDataInicio(dataInicio,"agenda", "dataAgendamento"))
                .and(specification.subColumnfindByDataFim(dataFim, "agenda", "dataAgendamento"))
                .and(specification.findLikeBySubSubColumn("paciente", "pessoa", "nome", nomePaciente))
                .and(specification.findLikeBySubSubColumn("paciente", "pessoa", "cpf", CodeUtils.getDigtsOnly(cpfPaciente)))
                .and(specification.findLikeBySubSubColumn("medico", "pessoa", "nome", nomeMedico));
    }

    @Transactional
    public AgendaConsulta inserir(AgendaConsultaCadastroDTO agendaConsultaCadastroDTO) {
        return Optional.ofNullable(agendaConsultaCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    @Transactional
    public Long createConsulta(Long id, Authentication auth) {
        var agendaConsutla = this.findById(id);

        var consultaCadastro = ConsultaCadastroDTO.builder()
                .idAgendaConsulta(agendaConsutla.getId())
                .tipo(agendaConsutla.getTipoConsulta().name())
                .idPaciente(agendaConsutla.getPaciente().getId())
                .idMedico(agendaConsutla.getMedico().getId())
                .build();

        var consulta = consultaService.inserir(consultaCadastro, auth);
        consulta.setAgendaConsulta(agendaConsutla);
        this.consultaService.save(consulta);
        return consulta.getId();
    }

    public AgendaConsulta update(Long id, AgendaConsultaCadastroDTO agendaConsultaCadastroDTO) {
        return Optional.ofNullable(agendaConsultaCadastroDTO)
                .map(acDto -> validarUpdate(acDto, id))
                .map(agendaConsulta -> prepareUpdate(agendaConsulta, id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var agendaConsulta = this.findById(id);

        Optional.ofNullable(agendaConsulta)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }


    @Override
    public AgendaConsultaCadastroDTO validarInsert(AgendaConsultaCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public AgendaConsultaCadastroDTO validarUpdate(AgendaConsultaCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(AgendaConsulta entity) {

    }

    @Override
    public AgendaConsulta prepareInsert(AgendaConsultaCadastroDTO dtoCadastro) {
        var agendaCadastro = AgendaCadastroDTO.builder()
                .dataAgendamento(dtoCadastro.getDataAgendamento())
                .descricao(dtoCadastro.getDescricao())
                .build();
        var agenda = agendaService.inserir(agendaCadastro);

        var agendaConsulta = AgendaConsultaConverter.toEntity(dtoCadastro);

        var medico = this.medicoService.findById(dtoCadastro.getIdMedico());
        var paciente = this.pacienteService.findById(dtoCadastro.getIdPaciente());

        agendaConsulta.setMedico(medico);
        agendaConsulta.setPaciente(paciente);
        agendaConsulta.setAgenda(agenda);
        agendaConsulta.setTipoConsulta(TipoConsultaEnum.valueOf(dtoCadastro.getTipoConsulta()));

        return agendaConsulta;
    }

    @Override
    public AgendaConsulta prepareUpdate(AgendaConsultaCadastroDTO dtoCadastro, Long id) {
        var agendaConsulta = this.findById(id);
        var agenda = agendaConsulta.getAgenda();

        agenda.setDataAgendamento(dtoCadastro.getDataAgendamento());
        agenda.setDescricao(dtoCadastro.getDescricao());
        this.agendaService.save(agenda);

        agendaConsulta.setMedico(medicoService.findById(dtoCadastro.getIdMedico()));
        agendaConsulta.setPaciente(pacienteService.findById(dtoCadastro.getIdPaciente()));
        return agendaConsulta;
    }
}
