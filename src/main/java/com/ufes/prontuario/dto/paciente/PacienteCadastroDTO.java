package com.ufes.prontuario.dto.paciente;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PacienteCadastroDTO {

    private BigDecimal peso;
    private BigDecimal altura;
    private PessoaCadastroDTO pessoaCadastroDTO;

}
