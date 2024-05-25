package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.usuario.UsuarioCadastroDTO;
import com.ufes.prontuario.dto.usuario.UsuarioConverter;
import com.ufes.prontuario.enums.StatusEnum;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Usuario;
import com.ufes.prontuario.repository.UsuarioRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IBaseService<UsuarioCadastroDTO, Usuario> {

    private final UsuarioRepository repository;

    public Usuario findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", id));
    }

    public Usuario salvar(UsuarioCadastroDTO usuarioCadastro) {

        var encryptPass = new BCryptPasswordEncoder().encode(usuarioCadastro.getSenha());
        usuarioCadastro.setSenha(encryptPass);

        var usuario = UsuarioConverter.toEntity(usuarioCadastro);
        return this.repository.save(usuario);
    }

    public Page<Usuario> filter(
            Long id, String login, String nome, Pageable pageable) {
        var specification = this.prepareSpecification(id, login, nome);

        return this.repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    public Usuario findByPessoa(Long idPessoa) {
        return this.repository.findByPessoaId(idPessoa);
    }

    public Usuario ativar(Long idUsuario) {
        var usuario = this.findById(idUsuario);
        usuario.setStatus(StatusEnum.ATIVO);

        return this.repository.save(usuario);
    }

    public Usuario inativar(Long idUsuario) {
        var usuario = this.findById(idUsuario);
        usuario.setStatus(StatusEnum.INATIVO);

        return this.repository.save(usuario);
    }

    private Specification<Usuario> prepareSpecification(
            Long id, String login, String nome) {
        final var specification = new BaseSpecification<Usuario>();

        return specification
                .and(specification.findById(id))
                .and(specification.findLikeBySubColumn("pessoa", "nome", nome))
                .and(specification.findLikeByColumn("login", login));
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
