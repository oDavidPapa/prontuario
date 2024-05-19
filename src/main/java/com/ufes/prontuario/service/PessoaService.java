package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import com.ufes.prontuario.dto.pessoa.PessoaConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Pessoa;
import com.ufes.prontuario.repository.PessoaRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PessoaService implements IBaseService<PessoaCadastroDTO, Pessoa>{

    private final PessoaRepository repository;

    public Pessoa findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa", id));
    }

    public List<Pessoa> listar() {
        return this.repository.findAll();
    }

    public Pessoa inserir(PessoaCadastroDTO pessoaCadastroDTO) {
        return Optional.ofNullable(pessoaCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Page<Pessoa> filter(Long id, String nome, String cpf, Pageable pageable) {
        var specification = this.prepareSpecification(id, nome, cpf);

        return this.repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    private Specification<Pessoa> prepareSpecification(Long id, String nome, String cpf) {
        final var specification = new BaseSpecification<Pessoa>();

        return specification
                .and(specification.findById(id))
                .and(specification.findLikeByColumn("nome", nome))
                .and(specification.findLikeByColumn( "cpf", cpf));

    }

    public Pessoa update(Long id, PessoaCadastroDTO pessoaCadastroDTO) {
        return Optional.ofNullable(pessoaCadastroDTO)
                .map(pDto -> validarUpdate(pDto, id))
                .map(pessoa -> prepareUpdate(pessoa ,id))
                .map(this.repository::save)
                .orElseThrow();
    }


    @Override
    public PessoaCadastroDTO validarInsert(PessoaCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public PessoaCadastroDTO validarUpdate(PessoaCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Pessoa entity) {

    }

    @Override
    public Pessoa prepareInsert(PessoaCadastroDTO dtoCadastro) {
        var pessoa = PessoaConverter.toEntity(dtoCadastro);

        return pessoa;
    }

    @Override
    public Pessoa prepareUpdate(PessoaCadastroDTO dtoCadastro, Long id) {
        var pessoa = this.findById(id);

        pessoa.setNome(dtoCadastro.getNome());
        pessoa.setCpf(dtoCadastro.getCpf());
        pessoa.setDataNascimento(dtoCadastro.getDataNascimento());

        return pessoa;
    }
}
