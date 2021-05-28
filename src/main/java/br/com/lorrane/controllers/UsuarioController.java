package br.com.lorrane.controllers;

import br.com.lorrane.controllers.dto.UsuarioDTO;
import br.com.lorrane.mapper.UsuarioMapper;
import br.com.lorrane.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = UsuarioController.PATH)
public class UsuarioController {

    public static final String PATH = "/usuarios";

    private final UsuarioMapper usuarioMapper;
    private final UsuarioService usuarioService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO dto) {
        UsuarioDTO usuarioDTO = Optional.of(dto)
                .map(this.usuarioMapper::fromDTO)
                .map(this.usuarioService::cadastrar)
                .map(this.usuarioMapper::toDTO)
                .orElseThrow();
        return ResponseEntity.created(URI.create(UsuarioController.PATH + usuarioDTO.getId())).body(usuarioDTO);
    }
}
