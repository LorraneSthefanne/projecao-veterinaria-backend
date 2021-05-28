package br.com.lorrane.controllers;

import br.com.lorrane.controllers.dto.PaginaDTO;
import br.com.lorrane.controllers.dto.PessoaDTO;
import br.com.lorrane.controllers.dto.novo.PessoaNovoDTO;
import br.com.lorrane.mapper.PessoaMapper;
import br.com.lorrane.models.PessoaFiltro;
import br.com.lorrane.services.PessoaService;
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
@RequestMapping(value = PessoaController.PATH)
public class PessoaController {

    public static final String PATH = "/pessoas";

    private final PessoaMapper pessoaMapper;
    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDTO> cadastrar(@RequestBody PessoaNovoDTO novoDTO) {
        PessoaDTO pessoa = Optional.of(novoDTO)
                .map(pessoaMapper::fromDTO)
                .map(pessoaService::cadastrar)
                .map(pessoaMapper::toDTO)
                .orElseThrow();
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> buscar(@PathVariable("id") UUID id) {
        return Optional.of(id)
                .map(pessoaService::buscar)
                .map(pessoaMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @GetMapping
    public ResponseEntity<PaginaDTO<PessoaDTO>> pesquisar(PessoaFiltro filtro,
                                                          @RequestParam(value = "pagina", defaultValue = "0") int pagina,
                                                          @RequestParam(value = "tamanho", defaultValue = "10") int tamanho) {
        return Optional.of(filtro)
                .map(f -> pessoaService.buscar(f, pagina, tamanho))
                .map(pessoaMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> atualizar(@PathVariable("id") UUID id, @RequestBody PessoaDTO pessoaDTO) {
        return Optional.of(pessoaDTO)
                .map(pessoaMapper::fromDTO)
                .map(p -> pessoaService.atualizar(id, p))
                .map(pessoaMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") UUID id) {
        Optional.of(id)
                .ifPresent(pessoaService::remover);
        return ResponseEntity.noContent().build();
    }

}
