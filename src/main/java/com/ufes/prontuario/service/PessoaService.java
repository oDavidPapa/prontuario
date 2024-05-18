package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import com.ufes.prontuario.dto.pessoa.PessoaConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Pessoa;
import com.ufes.prontuario.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PessoaService implements IBaseService<PessoaCadastroDTO, Pessoa>{

    private final PessoaRepository repository;
    private final ContatoService contatoService;

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

        var contato = this.contatoService.findById(dtoCadastro.getIdContato());
        pessoa.setContato(contato);

        return pessoa;
    }
}
