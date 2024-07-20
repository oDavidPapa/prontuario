package com.ufes.prontuario.dto.pessoa;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.model.Pessoa;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
public class PessoaConverter {

    public static PessoaDTO toDTO(Pessoa pessoa) {
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo())
                .cpf(pessoa.getCpf())
                .dataNascimento(pessoa.getDataNascimento())
                .build();
    }

    public static Pessoa toEntity(PessoaCadastroDTO pessoaCadastroDTO) {

        var pessoa = new Pessoa();
        pessoa.setCpf(StringUtils.getDigits(pessoaCadastroDTO.getCpf()));
        pessoa.setNome(pessoaCadastroDTO.getNome());
        pessoa.setSexo(pessoaCadastroDTO.getSexo());
        pessoa.setDataNascimento(pessoaCadastroDTO.getDataNascimento());
        pessoa.setAuditoria(new Auditoria());

        return pessoa;
    }
}
