package com.ufes.prontuario.dto.auth;


import com.ufes.prontuario.dto.usuario.UsuarioCadastroDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationConverter {

    public static UsuarioCadastroDTO authToDTOCadastro(AuthenticationRequestDTO authenticationRequestDTO) {
        return UsuarioCadastroDTO.builder()
                .login(authenticationRequestDTO.getLogin())
                .senha(authenticationRequestDTO.getSenha())
                .role(authenticationRequestDTO.getRole())
                .build();
    }
}
