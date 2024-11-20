package com.ufes.prontuario.dto.endereco;

import com.ufes.prontuario.model.Endereco;

public class EnderecoConverter {

    public static Endereco toEntity(EnderecoCadastroDTO enderecoCadastroDTO) {
        return Endereco.builder()
                .logradouro(enderecoCadastroDTO.getLogradouro())
                .numero(enderecoCadastroDTO.getNumero())
                .complemento(enderecoCadastroDTO.getComplemento())
                .bairro(enderecoCadastroDTO.getBairro())
                .cidade(enderecoCadastroDTO.getCidade())
                .cep(enderecoCadastroDTO.getCep())
                .uf(enderecoCadastroDTO.getUf())
                .pais(enderecoCadastroDTO.getPais())
                .build();

    }

    public static EnderecoDTO toDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .cep(endereco.getCep())
                .uf(endereco.getUf())
                .pais(endereco.getPais())
                .idPessoa(endereco.getPessoa().getId())
                .build();
    }
}
