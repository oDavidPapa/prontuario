package com.ufes.prontuario.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Paciente {

    @Id
    @SequenceGenerator(name = "seq_paciente", sequenceName = "seq_paciente", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_paciente")
    private Long id;

    @Column
    private BigDecimal peso;

    @Column
    private BigDecimal altura;

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
}
