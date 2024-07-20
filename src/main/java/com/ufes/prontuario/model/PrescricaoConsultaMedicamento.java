package com.ufes.prontuario.model;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @Embedded
    private Auditoria auditoria;

    @PreUpdate
    @PrePersist
    private void createdBy() {
        if(Objects.isNull(auditoria.getCreatedBy())) {
            auditoria.setCreatedBy("system");
        }
        if(Objects.isNull(auditoria.getLastModifiedBy())) {
            auditoria.setLastModifiedBy("system");
        }
    }
}
