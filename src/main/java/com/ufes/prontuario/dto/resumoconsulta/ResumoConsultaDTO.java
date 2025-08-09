package com.ufes.prontuario.dto.resumoconsulta;

import com.ufes.prontuario.dto.doenca.CidDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumoConsultaDTO {

    private DadosPacienteDTO dadosPaciente;
    private DadosConsultaDTO dadosConsulta;
    private List<CidDTO> cids;
    private List<DadosPrescricaoDTO> prescricoes;
    private String tratamento;
    private List<String> examesSolicitados;
    private List<String> alergias;
    private String diagnostico;

}
