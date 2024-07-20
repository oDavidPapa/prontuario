package com.ufes.prontuario.dto.usuario;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioCadastroDTO {

    private String login;
    private String senha;
    private String role;
    private Long idPessoa;
}
