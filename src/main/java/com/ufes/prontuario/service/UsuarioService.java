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
    private final MedicoService medicoService;


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

    @Transactional
    public Usuario update(Long id, UsuarioCadastroDTO usuarioCadastro) {

        var pessoa = this.pessoaService.update(usuarioCadastro.getIdPessoa(), usuarioCadastro.getPessoaCadastro());

        var contatoCadastro = usuarioCadastro.getContatoCadastro();
        var contato = this.contatoService.getContatoPrincipalByPessoa(pessoa.getId());

        if(Objects.nonNull(contato)) {
            this.contatoService.update(contato.getId(), contatoCadastro);
        } else {
            contatoCadastro.setIdPessoa(pessoa.getId());
            this.contatoService.inserir(contatoCadastro);
        }

        var usuario = this.findById(id);
        usuario.setLogin(usuarioCadastro.getLogin());
        usuario.setRole(usuario.getRole());

        usuario.setPessoa(pessoa);

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

    public UsuarioDTO completeDTO(UsuarioDTO usuarioDTO) {

        var contato = this.contatoService.getContatoPrincipalByPessoa(usuarioDTO.getPessoa().getId());
        if(Objects.nonNull(contato)) {
            usuarioDTO.setContato(ContatoConverter.toDTO(contato));
        }

        if (RoleEnum.MEDICO.name().equals(usuarioDTO.getRole())) {
            var medico = this.medicoService.getMedicoByPessoaId(usuarioDTO.getPessoa().getId());
            usuarioDTO.setEspecialidade(medico.getEspecialidade());
            usuarioDTO.setCrm(medico.getCrm());
        }

        return usuarioDTO;
    }


    public Usuario alterarStatus(Long idUsuario) {
        var usuario = this.findById(idUsuario);
        var status = StatusEnum.ATIVO.equals(usuario.getStatus()) ? StatusEnum.INATIVO : StatusEnum.ATIVO;

        usuario.setStatus(status);
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
