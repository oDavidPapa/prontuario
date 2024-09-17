package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.contato.ContatoConverter;
import com.ufes.prontuario.dto.usuario.UsuarioCadastroDTO;
import com.ufes.prontuario.dto.usuario.UsuarioConverter;
import com.ufes.prontuario.dto.usuario.UsuarioDTO;
import com.ufes.prontuario.enums.RoleEnum;
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
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IBaseService<UsuarioCadastroDTO, Usuario> {

    private final UsuarioRepository repository;
    private final PessoaService pessoaService;
    private final ContatoService contatoService;

    public Usuario findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", id));
    }

    @Transactional
    public Usuario salvar(UsuarioCadastroDTO usuarioCadastro) {

        if(RoleEnum.ADMINISTRATIVO.name().equals(usuarioCadastro.getRole())) {
            var pessoa = this.pessoaService.inserir(usuarioCadastro.getPessoaCadastro());
            usuarioCadastro.setIdPessoa(pessoa.getId());

            var contatoCadastro = usuarioCadastro.getContatoCadastro();
            contatoCadastro.setIdPessoa(pessoa.getId());
            this.contatoService.inserir(contatoCadastro);
        }

        var encryptPass = new BCryptPasswordEncoder().encode(usuarioCadastro.getSenha());
        usuarioCadastro.setSenha(encryptPass);

        var usuario = UsuarioConverter.toEntity(usuarioCadastro);
        usuario.setPessoa(this.pessoaService.findById(usuarioCadastro.getIdPessoa()));

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

    public UsuarioDTO setContatoUsuario(UsuarioDTO usuarioDTO) {
        var contato = this.contatoService.getContatoPrincipalByPessoa(usuarioDTO.getPessoa().getId());
        usuarioDTO.setContato(ContatoConverter.toDTO(contato));
        return usuarioDTO;
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
        return dtoCadastro;
    }

    @Override
    public UsuarioCadastroDTO validarUpdate(UsuarioCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
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
