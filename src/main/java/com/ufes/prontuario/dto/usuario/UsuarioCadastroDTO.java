package com.ufes.prontuario.dto.usuario;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioCadastroDTO {

    private Long id;
    private String login;
    private String senha;
    private String role;

    private Long idPessoa;
    private PessoaCadastroDTO pessoaCadastro;
}
