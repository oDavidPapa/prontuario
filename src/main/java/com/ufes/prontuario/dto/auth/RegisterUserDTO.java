package com.ufes.prontuario.dto.auth;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserDTO {

    @NonNull
    private String login;
    @NonNull
    private String senha;
    @NonNull
    private String role;

    private Long idPessoa;
    private PessoaCadastroDTO pessoaCadastroDTO;
}
