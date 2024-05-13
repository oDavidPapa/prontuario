package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.usuario.UsuarioCadastroDTO;
import com.ufes.prontuario.dto.usuario.UsuarioConverter;
import com.ufes.prontuario.model.Usuario;
import com.ufes.prontuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IBaseService<UsuarioCadastroDTO, Usuario> {

    private final UsuarioRepository repository;

    public Usuario salvar(UsuarioCadastroDTO usuarioCadastro) {

        var encryptPass = new BCryptPasswordEncoder().encode(usuarioCadastro.getSenha());
        usuarioCadastro.setSenha(encryptPass);

        var usuario = UsuarioConverter.toEntity(usuarioCadastro);
        return this.repository.save(usuario);
    }

    public UserDetails findByLogin(String login) {
        return this.repository.findByLogin(login);
    }

    @Override
    public UsuarioCadastroDTO validarInsert(UsuarioCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public UsuarioCadastroDTO validarUpdate(UsuarioCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(Usuario entity) {

    }

    @Override
    public Usuario prepareInsert(UsuarioCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public Usuario prepareUpdate(UsuarioCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
