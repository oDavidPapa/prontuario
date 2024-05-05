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
@Table(name = "prescricao_consulta_medicamento")
public class PrescricaoConsultaMedicamento {

    @Id
    @SequenceGenerator(name = "seq_tratamento", sequenceName = "seq_tratamento", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tratamento")
    private Long id;

    @Column
    private String dosagem;

    @Column(name = "instrucao_uso")
    private String instrucaoUso;


    @OneToOne
    @JoinColumn(name = "id_prescricao")
    private Prescricao prescricao;

    @OneToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;
}
