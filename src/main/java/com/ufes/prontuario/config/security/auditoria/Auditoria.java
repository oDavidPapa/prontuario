package com.ufes.prontuario.config.security.auditoria;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Embeddable
public class Auditoria {

        @CreatedDate
        @Column(name = "created_date")
        private LocalDateTime createdDate;

        @CreatedBy
        @Column(name = "created_by")
        private String createdBy;

        @LastModifiedDate
        @Column(name = "last_modified_date")
        private LocalDateTime lastModifiedDate;

        @LastModifiedBy
        @Column(name = "last_modified_by")
        private String lastModifiedBy;
}
