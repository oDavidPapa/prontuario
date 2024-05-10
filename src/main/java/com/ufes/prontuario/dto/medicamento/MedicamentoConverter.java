package com.ufes.prontuario.dto.medicamento;

import com.ufes.prontuario.model.Medicamento;

public class MedicamentoConverter {

    public static Medicamento toEntity(MedicamentoCadastroDTO medicamentoCadastroDTO) {

        var medicamento = new Medicamento();
        medicamento.setDescricao(medicamentoCadastroDTO.getDescricao());

        return medicamento;
    }

    public static MedicamentoDTO toDTO(Medicamento medicamento) {
        return MedicamentoDTO.builder()
                .id(medicamento.getId())
                .descricao(medicamento.getDescricao())
                .build();
    }
}
