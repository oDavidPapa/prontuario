package com.ufes.prontuario.dto.paciente;

import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PacienteDTO {

    private Long id;
    private BigDecimal peso;
    private BigDecimal altura;
    private PessoaDTO pessoa;
}
