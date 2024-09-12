package com.ufes.prontuario.model;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Paciente {

    @Id
    @SequenceGenerator(name = "seq_paciente", sequenceName = "seq_paciente", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_paciente")
    private Long id;

    @Column(name = "peso", precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(name = "altura", precision = 5, scale = 2)
    private BigDecimal altura;

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

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
