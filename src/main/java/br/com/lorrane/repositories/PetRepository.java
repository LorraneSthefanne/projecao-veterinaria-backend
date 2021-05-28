package br.com.lorrane.repositories;

import br.com.lorrane.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID>, QuerydslPredicateExecutor<Pet> {
}
