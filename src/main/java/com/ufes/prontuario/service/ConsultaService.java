package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.consulta.ConsultaCadastroDTO;
import com.ufes.prontuario.dto.consulta.ConsultaConverter;
import com.ufes.prontuario.enums.TipoConsultaEnum;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Consulta;
import com.ufes.prontuario.repository.ConsultaRepository;
import com.ufes.prontuario.specification.ConsultaSpecification;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConsultaService implements IBaseService<ConsultaCadastroDTO, Consulta> {

    private final ConsultaRepository repository;

    private MedicoService medicoService;
    private PacienteService pacienteService;
    private UsuarioService usuarioService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Autowired
    public void setMedicoService(MedicoService medicoService) {
        this.medicoService = medicoService;
    }


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
                .and(specification.findLikeBySubSubColumn("paciente", "pessoa", "nome", nomePaciente))
                .and(specification.findLikeBySubSubColumn( "medico" ,"pessoa", "nome", nomeMedico));
    }

    public List<Consulta> listar() {
        return this.repository.findAll();
    }

    public Consulta inserir(ConsultaCadastroDTO consultaCadastroDTO, Authentication auth) {
        consultaCadastroDTO.setLoginMedico(auth.getName());

        return Optional.of(consultaCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Consulta save(Consulta consulta) {
        return repository.save(consulta);
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

        var usuario = usuarioService.findByUsuarioLogin(dtoCadastro.getLoginMedico());
        var medico = medicoService.getMedicoByPessoaId(usuario.getPessoa().getId());
        var paciente = pacienteService.findById(dtoCadastro.getIdPaciente());

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        return consulta;
    }

    @Override
    public Consulta prepareUpdate(ConsultaCadastroDTO dtoCadastro, Long id) {
        var consulta = this.findById(id);

        consulta.setTipo(TipoConsultaEnum.valueOf(dtoCadastro.getTipo()));
        consulta.setAnamnese(dtoCadastro.getAnamnese());
        consulta.setPaciente(pacienteService.findById(dtoCadastro.getIdPaciente()));

        return consulta;
    }
}
