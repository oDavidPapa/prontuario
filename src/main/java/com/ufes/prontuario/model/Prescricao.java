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
public class Prescricao {

    @Id
    @SequenceGenerator(name = "seq_prescricao", sequenceName = "seq_prescricao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prescricao")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;
}
