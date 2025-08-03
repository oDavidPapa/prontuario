package com.ufes.prontuario.dto.prescricaomedicamento;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.medicamento.MedicamentoConverter;
import com.ufes.prontuario.dto.prescricao.PrescricaoConverter;
import com.ufes.prontuario.model.PrescricaoConsultaMedicamento;

public class PrescricaoConsultaMedicamentoConverter {

    public static PrescricaoConsultaMedicamentoDTO toDTO(PrescricaoConsultaMedicamento prescricaoConsultaMedicamento) {
        return PrescricaoConsultaMedicamentoDTO.builder()
                .id(prescricaoConsultaMedicamento.getId())
                .prescricao(PrescricaoConverter.toDTO(prescricaoConsultaMedicamento.getPrescricao()))
                .medicamento(prescricaoConsultaMedicamento.getMedicamento())
                .instrucaoUso(prescricaoConsultaMedicamento.getInstrucaoUso())
                .observacao(prescricaoConsultaMedicamento.getObservacao())
                .build();
    }

    public static PrescricaoConsultaMedicamento toEntity(PrescricaoConsultaMedicamentoCadastroDTO prescricaoMedicamentoCadastroDTO) {

        var prescricaoConsultaMedicamento = new PrescricaoConsultaMedicamento();
        prescricaoConsultaMedicamento.setMedicamento(prescricaoMedicamentoCadastroDTO.getMedicamento());
        prescricaoConsultaMedicamento.setInstrucaoUso(prescricaoMedicamentoCadastroDTO.getInstrucaoUso());
        prescricaoConsultaMedicamento.setObservacao(prescricaoMedicamentoCadastroDTO.getObservacao());
        prescricaoConsultaMedicamento.setAuditoria(new Auditoria());

        return prescricaoConsultaMedicamento;
    }
}
