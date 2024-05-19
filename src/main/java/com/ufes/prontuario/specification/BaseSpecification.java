package com.ufes.prontuario.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class BaseSpecification<T> implements Specification<T> {

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isNotNull(root.get("id"));
    }


    public Specification<T> findLikeByNome(@Nullable String nome) {
        return Optional.ofNullable(nome)
                .map(s -> prepareLikeSpecification("nome", s))
                .orElse(null);
    }

    public Specification<T> findById(@Nullable Long id) {
        return Optional.ofNullable(id)
                .map(i -> prepareEqualsSpecification("id", i))
                .orElse(null);
    }

    public Specification<T> findLikeByDescricao(@Nullable String descricao) {
        return Optional.ofNullable(descricao)
                .map(s -> prepareLikeSpecification("descricao", s))
                .orElse(null);
    }

    public Specification<T> findLikeByColumn(String column, @Nullable String nome) {
        return Optional.ofNullable(nome)
                .map(s -> prepareLikeSpecification(column, s))
                .orElse(null);
    }

    public Specification<T> findLikeBySubColumn(String column, String campo,  @Nullable String nome) {
        return Optional.ofNullable(nome)
                .map(s -> prepareLikeSubSpecification(column,campo, s))
                .orElse(null);
    }

    @NonNull
    protected Specification<T> prepareEqualsSpecification(@NonNull String columnName, @NonNull Object value) {
        return (root, query, builder) -> builder.equal(root.get(columnName), value);
    }

    @NonNull
    protected Specification<T> prepareNotEqualsSpecification(@NonNull String columnName, @NonNull Object value) {
        return (root, query, builder) -> builder.notEqual(root.get(columnName), value);
    }

    @NonNull
    protected Specification<T> prepareLikeSpecification(@NonNull String columnName, @NonNull String value) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(columnName)),
                "%" + value.toLowerCase() + "%");
    }

    @NonNull
    protected Specification<T> prepareLikeSubSpecification(@NonNull String columnName, @NonNull String campoName,
                                                           @NonNull String value) {
        return (root, query, builder) -> builder.like(builder.lower(root.join(columnName)
                .get(campoName)), "%" + value.toLowerCase() + "%");
    }

    public Specification<T> findBySubColumnId(@Nullable String columnName, String campoName, Long valor) {
        return Optional.ofNullable(valor)
                .map(id -> prepareEqualSubSpecification(columnName, campoName, id))
                .orElse(null);
    }


    @NonNull
    protected Specification<T> prepareEqualSubSpecification(
            @NonNull String columnName, @NonNull String campoName, @NonNull Long value) {
        return (root, query, builder) -> builder.equal(root.join(columnName).get(campoName), value);
    }
}
