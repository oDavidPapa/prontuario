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
public class Tratamento {

    @Id
    @SequenceGenerator(name = "seq_tratamento", sequenceName = "seq_tratamento", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tratamento")
    private Long id;

    @Column
    private String tratamento;

    @Column
    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;

}
