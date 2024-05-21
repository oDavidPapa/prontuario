package com.ufes.prontuario.dto.medico;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MedicoCadastroDTO {

    private String crm;
    private String especialidade;
    private PessoaCadastroDTO pessoaCadastroDTO;
}
