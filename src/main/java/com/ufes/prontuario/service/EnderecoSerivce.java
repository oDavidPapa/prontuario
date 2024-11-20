package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.endereco.EnderecoCadastroDTO;
import com.ufes.prontuario.dto.endereco.EnderecoConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Endereco;
import com.ufes.prontuario.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EnderecoSerivce implements  IBaseService<EnderecoCadastroDTO, Endereco>{

    private final EnderecoRepository repository;
    private final PessoaService pessoaService;

    public Endereco findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Endereco", id));
    }

    public Endereco findByPessoaId(Long idPessoa) {
        return this.repository.findByPessoaId(idPessoa);
    }

    public Endereco inserir(EnderecoCadastroDTO enderecoCadastroDTO) {
        return Optional.ofNullable(enderecoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Endereco update(Long id, EnderecoCadastroDTO enderecoCadastroDTO) {
        return Optional.ofNullable(enderecoCadastroDTO)
                .map(dDto -> validarUpdate(dDto, id))
                .map(endereco -> prepareUpdate(endereco ,id))
                .map(this.repository::save)
                .orElseThrow();
    }


    public void delete(Long id) {
        var endereco = this.findById(id);

        Optional.ofNullable(endereco)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }

    @Override
    public EnderecoCadastroDTO validarInsert(EnderecoCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public EnderecoCadastroDTO validarUpdate(EnderecoCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Endereco entity) {

    }

    @Override
    public Endereco prepareInsert(EnderecoCadastroDTO dtoCadastro) {
        var pessoa = this.pessoaService.findById(dtoCadastro.getIdPessoa());
        var endereco = EnderecoConverter.toEntity(dtoCadastro);
        endereco.setPessoa(pessoa);
        return endereco;
    }

    @Override
    public Endereco prepareUpdate(EnderecoCadastroDTO dtoCadastro, Long id) {
        var endereco = this.findById(id);
        endereco.setLogradouro(dtoCadastro.getLogradouro());
        endereco.setNumero(dtoCadastro.getNumero());
        endereco.setComplemento(dtoCadastro.getComplemento());
        endereco.setBairro(dtoCadastro.getBairro());
        endereco.setCidade(dtoCadastro.getCidade());
        endereco.setCep(dtoCadastro.getCep());
        endereco.setUf(dtoCadastro.getUf());
        endereco.setPais(dtoCadastro.getPais());

        return endereco;
    }
}
