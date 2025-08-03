package com.ufes.prontuario.dto.prescricaomedicamento;

import com.ufes.prontuario.dto.medicamento.MedicamentoDTO;
import com.ufes.prontuario.dto.prescricao.PrescricaoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PrescricaoConsultaMedicamentoDTO {

    private Long id;
    private String medicamento;
    private String instrucaoUso;
    private String observacao;
    private PrescricaoDTO prescricao;
}
