package com.ufes.prontuario.dto.usuario;

import com.ufes.prontuario.dto.pessoa.PessoaDTO;
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
    private String status;
    private PessoaDTO pessoa;
}
