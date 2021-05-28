package br.com.lorrane.services.impl;

import br.com.lorrane.entities.Pet;
import br.com.lorrane.entities.QPet;
import br.com.lorrane.exceptions.Mensagem;
import br.com.lorrane.exceptions.NotFoundException;
import br.com.lorrane.expression.BooleanExpressionBuilder;
import br.com.lorrane.models.PetFiltro;
import br.com.lorrane.repositories.PetRepository;
import br.com.lorrane.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    @Transactional
    public Pet cadastrar(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    @Transactional
    public Pet atualizar(UUID id, Pet pet) {
        Pet petEncontrado = buscar(id);
        petEncontrado.setNome(pet.getNome());
        petEncontrado.setPeso(pet.getPeso());
        petEncontrado.setRaca(pet.getRaca());
        petEncontrado.setEspecie(pet.getEspecie());
        petEncontrado.setDataNascimento(pet.getDataNascimento());
        return petRepository.save(petEncontrado);
    }

    @Override
    public Pet buscar(UUID id) {
        return petRepository.findById(id)
                .orElseThrow(NotFoundException.supplier(Mensagem.PET_NAO_ENCONTRADO));
    }

    @Override
    public Page<Pet> buscar(@Valid PetFiltro filtro, int pagina, int tamanho) {
        QPet pet = QPet.pet;
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var expression = new BooleanExpressionBuilder(pet.isNotNull())
                .notNullAnd(pet.pessoa.id::eq, filtro.getIdPessoa())
                .notNullAnd(pet.nome::containsIgnoreCase, filtro.getNome())
                .build();
        return petRepository.findAll(expression, pageRequest);
    }

    @Override
    @Transactional
    public void remover(UUID id) {
        Pet pet = buscar(id);
        petRepository.delete(pet);
    }
}
