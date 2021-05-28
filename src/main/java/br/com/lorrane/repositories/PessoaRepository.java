package br.com.lorrane.repositories;

import br.com.lorrane.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID>, QuerydslPredicateExecutor<Pessoa> {

    Boolean existsByEmailAndIdIsNot(String email, UUID id);

    Boolean existsByEmail(String email);

    Boolean existsByDocumento(String documento);

    Boolean existsByDocumentoAndIdIsNot(String documento, UUID id);
}
