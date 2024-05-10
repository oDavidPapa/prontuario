package com.ufes.prontuario.dto.pessoa;

import com.ufes.prontuario.dto.contato.ContatoConverter;
import com.ufes.prontuario.dto.usuario.UsuarioConverter;
import com.ufes.prontuario.model.Pessoa;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@AllArgsConstructor
public class PessoaConverter {

    public static PessoaDTO toDTO(Pessoa pessoa) {
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo())
                .cpf(pessoa.getCpf())
                .dataNascimento(pessoa.getDataNascimento())
                .usuario(Optional.ofNullable(pessoa.getUsuario())
                        .map(UsuarioConverter::toDTO)
                        .orElse(null))
                .contato(ContatoConverter.toDTO(pessoa.getContato()))
                .build();
    }

    public static Pessoa toEntity(PessoaCadastroDTO pessoaCadastroDTO) {

        var pessoa = new Pessoa();
        pessoa.setCpf(StringUtils.getDigits(pessoaCadastroDTO.getCpf()));
        pessoa.setNome(pessoaCadastroDTO.getNome());
        pessoa.setSexo(pessoaCadastroDTO.getSexo());
        pessoa.setDataNascimento(pessoaCadastroDTO.getDataNascimento());

        return pessoa;
    }
}
