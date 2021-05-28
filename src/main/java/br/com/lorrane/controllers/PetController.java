package br.com.lorrane.controllers;

import br.com.lorrane.controllers.dto.PaginaDTO;
import br.com.lorrane.controllers.dto.PetDTO;
import br.com.lorrane.controllers.dto.novo.PetNovoDTO;
import br.com.lorrane.mapper.PetMapper;
import br.com.lorrane.models.PetFiltro;
import br.com.lorrane.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = PetController.PATH)
public class PetController {

    public static final String PATH = "/pets";

    private final PetMapper petMapper;
    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetDTO> cadastrar(@RequestBody PetNovoDTO novoDTO) {
        PetDTO pet = Optional.of(novoDTO)
                .map(petMapper::fromDTO)
                .map(petService::cadastrar)
                .map(petMapper::toDTO)
                .orElseThrow();
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetDTO> buscar(@PathVariable("id") UUID id) {
        return Optional.of(id)
                .map(petService::buscar)
                .map(petMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @GetMapping
    public ResponseEntity<PaginaDTO<PetDTO>> pesquisar(PetFiltro filtro,
                                                       @RequestParam(value = "pagina", defaultValue = "0") int pagina,
                                                       @RequestParam(value = "tamanho", defaultValue = "10") int tamanho) {
        return Optional.of(filtro)
                .map(f -> petService.buscar(f, pagina, tamanho))
                .map(petMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PetDTO> atualizar(@PathVariable("id") UUID id, @RequestBody PetDTO petDTO) {
        return Optional.of(petDTO)
                .map(petMapper::fromDTO)
                .map(p -> petService.atualizar(id, p))
                .map(petMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") UUID id) {
        Optional.of(id).ifPresent(petService::remover);
        return ResponseEntity.noContent().build();
    }
}
