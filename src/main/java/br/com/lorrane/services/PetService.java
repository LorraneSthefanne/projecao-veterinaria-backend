package br.com.lorrane.services;

import br.com.lorrane.entities.Pet;
import br.com.lorrane.models.PetFiltro;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PetService {

    Pet cadastrar(Pet pet);

    Pet atualizar(UUID id, Pet pet);

    Pet buscar(UUID id);

    Page<Pet> buscar(PetFiltro filtro, int pagina, int tamanho);

    void remover(UUID id);
}
