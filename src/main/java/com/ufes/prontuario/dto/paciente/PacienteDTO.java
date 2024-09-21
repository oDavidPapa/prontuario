package com.ufes.prontuario.dto.paciente;

import com.ufes.prontuario.dto.contato.ContatoDTO;
import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PacienteDTO {

    private Long id;
    private String peso;
    private String altura;
    private PessoaDTO pessoa;
    private ContatoDTO contato;
}
