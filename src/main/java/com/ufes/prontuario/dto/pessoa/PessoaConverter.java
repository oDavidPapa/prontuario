package com.ufes.prontuario.dto.pessoa;

import com.ufes.prontuario.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class PessoaConverter {

    public static PessoaDTO toDTO(Pessoa pessoa) {
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo())
                .cpf(pessoa.getCpf())
                .dataNascimento(pessoa.getDataNascimento())
                .usuario(null)
                .contato(null)
                .build();
    }
}
