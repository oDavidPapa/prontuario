package com.ufes.prontuario.dto.alergiapaciente;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AlergiaPacienteCadastroDTO {

    private String descricao;
    private Long idPaciente;
}
