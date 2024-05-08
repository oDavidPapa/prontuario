package com.ufes.prontuario.dto.usuario;

import com.ufes.prontuario.enums.Role;
import com.ufes.prontuario.model.Usuario;
import lombok.Builder;

@Builder
public class UsuarioConverter {

    public static Usuario usuarioCadastroToEntity(UsuarioCadastroDTO usuarioCadastroDTO) {

        var usuario = new Usuario();
        usuario.setLogin(usuarioCadastroDTO.getLogin());
        usuario.setSenha(usuarioCadastroDTO.getSenha());
        usuario.setRole(Role.valueOf(usuarioCadastroDTO.getRole()));

        return usuario;
    }

    public static UsuarioDTO entityToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .role(usuario.getRole().name())
                .build();
    }
}
