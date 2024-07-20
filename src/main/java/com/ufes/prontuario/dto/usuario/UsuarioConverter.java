package com.ufes.prontuario.dto.usuario;

import com.ufes.prontuario.dto.pessoa.PessoaConverter;
import com.ufes.prontuario.enums.RoleEnum;
import com.ufes.prontuario.enums.StatusEnum;
import com.ufes.prontuario.model.Usuario;

public class UsuarioConverter {

    public static Usuario toEntity(UsuarioCadastroDTO usuarioCadastroDTO) {

        var usuario = new Usuario();
        usuario.setLogin(usuarioCadastroDTO.getLogin());
        usuario.setSenha(usuarioCadastroDTO.getSenha());
        usuario.setRole(RoleEnum.valueOf(usuarioCadastroDTO.getRole()));
        usuario.setStatus(StatusEnum.ATIVO);

        return usuario;
    }

    public static UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .role(usuario.getRole().name())
                .status(usuario.getStatus().name())
                .pessoa(PessoaConverter.toDTO(usuario.getPessoa()))
                .build();
    }
}
