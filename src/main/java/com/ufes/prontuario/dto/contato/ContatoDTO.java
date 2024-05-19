package com.ufes.prontuario.dto.contato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContatoDTO {

    private Long id;
    private String celular;
    private String telefone;
    private String email;
    private String tipoContato;
    private Long idPessoa;
}
