package com.ufes.prontuario.dto.contato;

import com.ufes.prontuario.model.Contato;

public class ContatoConverter {

    public static Contato toEntity(ContatoCadastroDTO contatoCadastroDTO) {

        var contato = new Contato();
        contato.setCelular(contatoCadastroDTO.getCelular());
        contato.setEmail(contatoCadastroDTO.getEmail());
        contato.setTelefone(contatoCadastroDTO.getTelefone());

        return contato;
    }

    public static ContatoDTO toDTO(Contato contato) {
        return ContatoDTO.builder()
                .celular(contato.getCelular())
                .email(contato.getEmail())
                .telefone(contato.getTelefone())
                .build();
    }
}
