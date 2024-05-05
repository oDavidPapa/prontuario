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
public class Exame {

    @Id
    @SequenceGenerator(name = "seq_exame", sequenceName = "seq_exame", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_exame")
    private Long id;

    @Column
    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;
}
