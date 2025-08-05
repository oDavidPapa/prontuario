package com.ufes.prontuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Arquivo {

    @Id
    @SequenceGenerator(name = "seq_arquivo", sequenceName = "seq_arquivo", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_arquivo")
    private Long id;

    @Column
    private String nome;

    @Column(name = "arquivo", columnDefinition = "bytea")
    @Lob
    private byte[] arquivo;

    @Column
    private String descricao;

    @Column
    private String tipo;

    @OneToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;
}
