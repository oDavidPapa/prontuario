package com.ufes.prontuario.dto.prescricaomedicamento;

import com.ufes.prontuario.dto.medicamento.MedicamentoConverter;
import com.ufes.prontuario.dto.prescricao.PrescricaoConverter;
import com.ufes.prontuario.model.PrescricaoConsultaMedicamento;

public class PrescricaoConsultaMedicamentoConverter {

    public static PrescricaoConsultaMedicamentoDTO toDTO(PrescricaoConsultaMedicamento prescricaoConsultaMedicamento) {
        return PrescricaoConsultaMedicamentoDTO.builder()
                .id(prescricaoConsultaMedicamento.getId())
                .prescricao(PrescricaoConverter.toDTO(prescricaoConsultaMedicamento.getPrescricao()))
                .medicamento(MedicamentoConverter.toDTO(prescricaoConsultaMedicamento.getMedicamento()))
                .dosagem(prescricaoConsultaMedicamento.getDosagem())
                .instrucaoUso(prescricaoConsultaMedicamento.getInstrucaoUso())
                .build();
    }

    public static PrescricaoConsultaMedicamento toEntity(PrescricaoConsultaMedicamentoCadastroDTO prescricaoMedicamentoCadastroDTO) {

        var prescricaoConsultaMedicamento = new PrescricaoConsultaMedicamento();
        prescricaoConsultaMedicamento.setDosagem(prescricaoMedicamentoCadastroDTO.getDosagem());
        prescricaoConsultaMedicamento.setInstrucaoUso(prescricaoMedicamentoCadastroDTO.getInstrucaoUso());

        return prescricaoConsultaMedicamento;
    }
}
