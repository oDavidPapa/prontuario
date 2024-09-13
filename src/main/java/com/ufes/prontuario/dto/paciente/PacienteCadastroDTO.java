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

    private Long id;
    private String peso;
    private String altura;
    private PessoaCadastroDTO pessoaCadastroDTO;

}
