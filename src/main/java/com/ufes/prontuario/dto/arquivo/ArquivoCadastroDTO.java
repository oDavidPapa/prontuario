package com.ufes.prontuario.dto.arquivo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ArquivoCadastroDTO {

    private String nome;
    private byte[] arquivo;
}
