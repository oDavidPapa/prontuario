package com.ufes.prontuario.repository;

import com.ufes.prontuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    UserDetails findByLogin(String login);

    Usuario findByPessoaId(Long pessoaId);

    @Query("SELECT u FROM Usuario u WHERE u.login = :login")
    Usuario findByUsuarioLogin(String login);
}
