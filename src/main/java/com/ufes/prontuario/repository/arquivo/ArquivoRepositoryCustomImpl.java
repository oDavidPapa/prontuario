package com.ufes.prontuario.repository.arquivo;

import com.ufes.prontuario.model.Arquivo;
import com.ufes.prontuario.model.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ArquivoRepositoryCustomImpl implements ArquivoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String INSERT_QUERY = """
                INSERT INTO arquivo (nome, arquivo, descricao, tipo, id_consulta)
                VALUES (:nome, :arquivo, :descricao, :tipo, :idConsulta)
                RETURNING id, nome, arquivo, descricao, tipo, id_consulta
            """;

    @Override
    public Arquivo insertAndReturnArquivo(String nome, byte[] conteudo, String descricao, String tipo, Long idConsulta) {
        final Object[] row = (Object[]) entityManager
                .createNativeQuery(INSERT_QUERY)
                .setParameter("nome", nome)
                .setParameter("arquivo", conteudo)
                .setParameter("descricao", descricao)
                .setParameter("tipo", tipo)
                .setParameter("idConsulta", idConsulta)
                .getSingleResult();

        return mapToArquivo(row);
    }

    @Override
    public List<Arquivo> findByIdConsulta(Long idConsulta) {
        String sql = "SELECT id, nome, arquivo, descricao, tipo, id_consulta FROM arquivo WHERE id_consulta = :idConsulta";
        List<Object[]> rows = entityManager.createNativeQuery(sql)
                .setParameter("idConsulta", idConsulta)
                .getResultList();

        return rows.stream()
                .map(this::mapToArquivo)
                .toList();
    }

    @Override
    public Optional<Arquivo> findById(Long id) {
        String sql = "SELECT id, nome, arquivo, descricao, tipo, id_consulta FROM arquivo WHERE id = :id";
        try {
            Object[] row = (Object[]) entityManager.createNativeQuery(sql)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.of(mapToArquivo(row));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM arquivo WHERE id = :id";
        int affected = entityManager.createNativeQuery(sql)
                .setParameter("id", id)
                .executeUpdate();
        return affected > 0;
    }

    private Arquivo mapToArquivo(Object[] row) {
        final Arquivo arquivo = new Arquivo();
        arquivo.setId(((Number) row[0]).longValue());
        arquivo.setNome((String) row[1]);
        arquivo.setArquivo((byte[]) row[2]);
        arquivo.setDescricao((String) row[3]);
        arquivo.setTipo((String) row[4]);

        if (row[5] != null) {
            final Consulta consulta = new Consulta();
            consulta.setId(((Number) row[5]).longValue());
            arquivo.setConsulta(consulta);
        }
        return arquivo;
    }
}
