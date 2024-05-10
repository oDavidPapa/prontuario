package com.ufes.prontuario.dto.medico;

import com.ufes.prontuario.dto.pessoa.PessoaConverter;
import com.ufes.prontuario.model.Medico;
import lombok.Builder;

@Builder
public class MedicoConverter {

    public static Medico toEntity(MedicoCadastroDTO medicoCadastroDTO) {

        var medico = new Medico();
        medico.setCrm(medicoCadastroDTO.getCrm());
        medico.setEspecialidade(medicoCadastroDTO.getEspecialidade());

        return medico;
    }

    public static MedicoDTO toDTO(Medico medico) {
        return MedicoDTO.builder()
                .id(medico.getId())
                .crm(medico.getCrm())
                .especialidade(medico.getEspecialidade())
                .pessoa(PessoaConverter.toDTO(medico.getPessoa()))
                .build();
    }
}
