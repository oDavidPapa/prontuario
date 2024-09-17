package com.ufes.prontuario.dto.auth;


import com.ufes.prontuario.dto.usuario.UsuarioCadastroDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationConverter {

    public static UsuarioCadastroDTO authToDTOCadastro(AuthenticationRequestDTO authenticationRequestDTO) {
        return UsuarioCadastroDTO.builder()
                .login(authenticationRequestDTO.getLogin())
                .senha(authenticationRequestDTO.getSenha())
                .build();
    }

    public static UsuarioCadastroDTO registerToDTOCadastro(RegisterUserDTO registerUserDTO) {
        return UsuarioCadastroDTO.builder()
                .login(registerUserDTO.getLogin())
                .senha(registerUserDTO.getSenha())
                .role(registerUserDTO.getRole())
                .idPessoa(registerUserDTO.getIdPessoa())
                .pessoaCadastro(registerUserDTO.getPessoaCadastroDTO())
                .contatoCadastro(registerUserDTO.getContatoCadastroDTO())
                .build();

    }
}
