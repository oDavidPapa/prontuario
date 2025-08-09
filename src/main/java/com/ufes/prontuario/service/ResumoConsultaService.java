package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.resumoconsulta.DadosConsultaDTO;
import com.ufes.prontuario.dto.resumoconsulta.DadosPacienteDTO;
import com.ufes.prontuario.dto.resumoconsulta.ResumoConsultaDTO;
import com.ufes.prontuario.model.AlergiaPaciente;
import com.ufes.prontuario.model.Exame;
import com.ufes.prontuario.model.PrescricaoConsultaMedicamento;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ResumoConsultaService {
    private final ConsultaService consultaService;
    private final DiagnosticoService diagnosticoService;
    private final TratamentoService tratamentoService;
    private final CidService cidService;
    private final PrescricaoConsultaMedicamentoService prescricaoConsultaMedicamentoService;
    private final AlergiaPacienteService alergiaPacienteService;
    private final ExameService exameService;


    public ResumoConsultaDTO getResumoConsulta(Long idConsulta) {
        var resumoConsulta = new ResumoConsultaDTO();
        var consulta = consultaService.findById(idConsulta);
        var medico = consulta.getMedico();
        var paciente = consulta.getPaciente();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
       var dataConsulta = consulta.getData().format(formatter);

        var dadosConsultaDTO = DadosConsultaDTO.builder()
                .tipo(consulta.getTipo().getDescricao())
                .medico(medico.getPessoa().getNome() +" / " + medico.getCrm())
                .especialidade(medico.getEspecialidade())
                .dataHora(dataConsulta)
                .build();

        var hoje = LocalDate.now();
        var idade = Period.between(paciente.getPessoa().getDataNascimento(), hoje).getYears();
        var dadosPaciente = DadosPacienteDTO.builder()
                .nome(paciente.getPessoa().getNome())
                .idade(idade)
                .sexo(paciente.getPessoa().getSexo() == 'M' ? "Masculino" : "Feminino")
                .build();

        var diagnostico = diagnosticoService.findById(idConsulta);
        var cids = cidService.findByDiagnostico(diagnostico.getId());
        var prescricoes = prescricaoConsultaMedicamentoService.findAllByConsulta(idConsulta).stream().map(PrescricaoConsultaMedicamento::getMedicamento).toList();
        var tratamento = tratamentoService.findByIdConsulta(idConsulta);
        var alergias = alergiaPacienteService.findAllByPaciente(paciente.getId()).stream().map(AlergiaPaciente::getDescricao).toList();
        var examesSolicitados = exameService.findAllByConsulta(idConsulta).stream().map(Exame::getDescricao).toList();

        return resumoConsulta;
    }
}
