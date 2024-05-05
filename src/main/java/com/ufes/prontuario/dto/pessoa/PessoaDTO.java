package com.ufes.prontuario.dto.pessoa;

import com.ufes.prontuario.dto.contato.ContatoDTO;
import com.ufes.prontuario.dto.usuario.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PessoaDTO {

    private Long id;
    private String nome;
    private String cpf;
    private char sexo;
    private LocalDate dataNascimento;
    private ContatoDTO contato;
    private UsuarioDTO usuario;
}
