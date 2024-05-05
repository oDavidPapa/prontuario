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
@Table(name = "resultado_exame")
public class ResultadoExame {

    @Id
    @SequenceGenerator(name = "seq_resultado_exame", sequenceName = "seq_resultado_exame", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_resultado_exame")
    private Long id;

    @Column
    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_exame")
    private Exame exame;

    @ManyToOne
    @JoinColumn(name = "arquivo")
    private Arquivo arquivo;
}
