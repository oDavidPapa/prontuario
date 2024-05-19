package com.ufes.prontuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Pessoa {

    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    Long id;

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column
    private char sexo;

    @Column
    private LocalDate dataNascimento;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
