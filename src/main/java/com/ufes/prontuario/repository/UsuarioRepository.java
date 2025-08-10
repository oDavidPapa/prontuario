package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Contato;
import com.ufes.prontuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    UserDetails findByLogin(String login);

    Usuario findByPessoaId(Long pessoaId);

    @Query("SELECT u FROM Usuario u WHERE u.login = :login")
    Usuario findByUsuarioLogin(String login);

    @Query(value = "" +
            "SELECT u.* FROM contato c " +
            "JOIN pessoa p ON c.id_pessoa = p.id " +
            "JOIN usuario u ON p.id = u.id_pessoa " +
            "WHERE c.tipo_contato = 'PRINCIPAL' " +
            "AND lower(c.email) = lower(:email) " +
            "LIMIT 1", nativeQuery = true)
    Usuario findFirstUsuarioByEmail(@Param("email") String email);
}
