package com.ufes.prontuario.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;
    private String login;
    private String senha;
    private String role;
}
