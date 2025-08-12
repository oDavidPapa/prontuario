package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.contato.ContatoCadastroDTO;
import com.ufes.prontuario.dto.contato.ContatoConverter;
import com.ufes.prontuario.enums.TipoContatoEnum;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Contato;
import com.ufes.prontuario.repository.ContatoRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2
public class ContatoService implements IBaseService<ContatoCadastroDTO, Contato> {

    private final ContatoRepository repository;
    private final PessoaService pessoaService;

    public Contato findById(Long id) {
        log.info("Buscando contato id={}", id);
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contato", id));
    }

    public List<Contato> listar() {
        return this.repository.findAll();
    }

    public Page<Contato> filter(Long idPessoa, String tipoContato, Pageable pageable) {
        var specification = this.prepareSpecification(idPessoa, tipoContato);

        return this.repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    private Specification<Contato> prepareSpecification(Long idPessoa, String tipoContato) {
        final var specification = new BaseSpecification<Contato>();

        return specification
                .and(specification.findLikeByColumn("tipoContato", tipoContato))
                .and(specification.findBySubColumnId("pessoa", "id", idPessoa));

    }

    public Contato inserir(ContatoCadastroDTO contatoCadastroDTO) {
        log.info("Inserindo contato...");
        return Optional.ofNullable(contatoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Contato update(Long id, ContatoCadastroDTO contatoCadastroDTO) {
        log.info("Update contato id={}", id);
        return Optional.ofNullable(contatoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(contato -> prepareUpdate(contato, id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        log.info("Delete contato id={}", id);
        var contato = this.findById(id);

        Optional.ofNullable(contato)
                .ifPresent(c -> {
                    this.validarDelete(c);
                    this.repository.delete(c);
                });
    }

    @Override
    public ContatoCadastroDTO validarInsert(ContatoCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public ContatoCadastroDTO validarUpdate(ContatoCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Contato entity) {

    }

    @Override
    public Contato prepareInsert(ContatoCadastroDTO dtoCadastro) {
        var contato = ContatoConverter.toEntity(dtoCadastro);
        var pessoa = this.pessoaService.findById(dtoCadastro.getIdPessoa());

        contato.setPessoa(pessoa);
        return contato;
    }

    public Contato getContatoPrincipalByPessoa(Long idPessoa) {
        return this.repository.findByPessoaIdAndTipoContato(idPessoa, TipoContatoEnum.PRINCIPAL);
    }

    @Override
    public Contato prepareUpdate(ContatoCadastroDTO dtoCadastro, Long id) {
        var contato = this.findById(id);

        contato.setCelular(StringUtils.getDigits(dtoCadastro.getCelular()));
        contato.setEmail(dtoCadastro.getEmail());
        contato.setTelefone(StringUtils.getDigits(dtoCadastro.getTelefone()));
        contato.setTipoContato(TipoContatoEnum.valueOf(dtoCadastro.getTipoContato()));

        return contato;
    }
}
