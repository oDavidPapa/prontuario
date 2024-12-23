package com.ufes.prontuario.dto.arquivo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ArquivoDTO {

    private Long id;
    private String nome;
    private byte[] arquivo;
}
