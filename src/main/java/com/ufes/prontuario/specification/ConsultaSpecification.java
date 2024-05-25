package com.ufes.prontuario.specification;

import com.ufes.prontuario.model.Consulta;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Optional;

public class ConsultaSpecification extends BaseSpecification<Consulta> {

    public Specification<Consulta> findByDataInicio(@Nullable LocalDate data) {
        return Optional.ofNullable(data)
                .map(d -> (Specification<Consulta>) (root, query, builder) -> builder
                        .greaterThanOrEqualTo(root.get("data"), d))
                .orElse(null);
    }

    public Specification<Consulta> findByDataFim(@Nullable LocalDate data) {
        return Optional.ofNullable(data)
                .map(d -> (Specification<Consulta>) (root, query, builder) -> builder
                        .lessThanOrEqualTo(root.get("data"), d))
                .orElse(null);
    }
}
