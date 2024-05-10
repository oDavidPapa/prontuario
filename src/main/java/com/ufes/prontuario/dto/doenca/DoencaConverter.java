package com.ufes.prontuario.dto.doenca;

import com.ufes.prontuario.model.Doenca;

public class DoencaConverter {

    public static Doenca toEntity(DoencaCadastroDTO doencaCadastroDTO) {

        var doenca = new Doenca();
        doenca.setDescricao(doencaCadastroDTO.getDescricao());
        doenca.setCid(doencaCadastroDTO.getCid());

        return doenca;
    }

    public static DoencaDTO toDTO(Doenca doenca) {
        return DoencaDTO.builder()
                .id(doenca.getId())
                .descricao(doenca.getDescricao())
                .cid(doenca.getCid())
                .build();
    }
}
