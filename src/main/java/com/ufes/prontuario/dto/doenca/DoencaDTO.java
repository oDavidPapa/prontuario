package com.ufes.prontuario.dto.doenca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DoencaDTO {

    private Long id;
    private String descricao;
    private String cid;
}
