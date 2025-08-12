package com.ufes.prontuario.dto.medico;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MedicoCadastroDTO {

    private String crm;
    private String especialidade;
    private PessoaCadastroDTO pessoaCadastroDTO;
}
