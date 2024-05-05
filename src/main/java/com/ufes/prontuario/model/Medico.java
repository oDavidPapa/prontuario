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
public class Medico {

    @Id
    @SequenceGenerator(name = "seq_medico", sequenceName = "seq_medico", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_medico")
    private Long id;

    @Column
    private String crm;

    @Column
    private String especialidade;

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
}
