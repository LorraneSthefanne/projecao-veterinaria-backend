package br.com.lorrane.mapper;

import br.com.lorrane.controllers.dto.UsuarioDTO;
import br.com.lorrane.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario fromDTO(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);
}
