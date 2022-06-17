package io.github.professorpaulojca.clientes.model.repository;

import io.github.professorpaulojca.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> { //<tipo da classe , tipo da chave primária>

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}
