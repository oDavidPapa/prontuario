package com.ufes.prontuario.model;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cid {

    @Id
    @SequenceGenerator(name = "seq_cid", sequenceName = "seq_cid", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cid")
    private Long id;

    @Column
    private String descricao;

    @Column
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_diagnostico")
    private Diagnostico diagnostico;

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
