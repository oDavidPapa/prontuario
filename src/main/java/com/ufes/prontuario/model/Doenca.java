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
public class Doenca {

    @Id
    @SequenceGenerator(name = "seq_doenca", sequenceName = "seq_doenca", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_doenca")
    private Long id;

    @Column
    private String descricao;

    @Column
    private String cid;
}
