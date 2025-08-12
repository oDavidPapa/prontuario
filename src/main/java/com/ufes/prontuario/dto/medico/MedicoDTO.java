package com.ufes.prontuario.dto.medico;

import com.ufes.prontuario.dto.contato.ContatoDTO;
import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MedicoDTO {

    private Long id;
    private String crm;
    private String especialidade;
    private PessoaDTO pessoa;
    private ContatoDTO contato;
}
