package com.ufes.prontuario.dto.contato;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContatoCadastroDTO {

    private String celular;
    private String telefone;
    private String email;
    private String tipoContato;
    private Long idPessoa;
}
