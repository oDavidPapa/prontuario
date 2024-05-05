package com.ufes.prontuario.dto.paciente;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PacienteCadastroDTO {

    private BigDecimal peso;
    private BigDecimal altura;
    private Long idPessoa;
}
