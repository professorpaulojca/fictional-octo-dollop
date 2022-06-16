package io.github.professorpaulojca.clientes.model.repository;

import io.github.professorpaulojca.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> { //tipo de dados que representa a chave primaria

}
