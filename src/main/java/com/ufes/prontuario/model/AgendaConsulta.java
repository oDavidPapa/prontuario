package com.ufes.prontuario.model;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.enums.TipoConsultaEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "agenda_consulta")
public class AgendaConsulta {

    @Id
    @SequenceGenerator(name = "seq_agenda_consulta", sequenceName = "seq_agenda_consulta", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_agenda_consulta")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @OneToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    @Enumerated(EnumType.STRING)
    private TipoConsultaEnum tipoConsulta;

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
