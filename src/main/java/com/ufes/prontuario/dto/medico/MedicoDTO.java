package com.ufes.prontuario.dto.medico;

import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MedicoDTO {

    private Long id;
    private String crm;
    private String especialidade;
    private PessoaDTO pessoa;
}
