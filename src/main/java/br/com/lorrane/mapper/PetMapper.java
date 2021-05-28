package br.com.lorrane.mapper;

import br.com.lorrane.controllers.dto.PaginaDTO;
import br.com.lorrane.controllers.dto.PetDTO;
import br.com.lorrane.controllers.dto.novo.PetNovoDTO;
import br.com.lorrane.entities.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mapping(target = "pessoa", expression = "java(new br.com.lorrane.entities.Pessoa(dto.getIdPessoa()))")
    Pet fromDTO(PetNovoDTO dto);

    Pet fromDTO(PetDTO dto);

    PetDTO toDTO(Pet pet);

    List<PetDTO> toDTO(List<Pet> pets);

    default PaginaDTO<PetDTO> toDTO(Page<Pet> pets) {
        return new PaginaDTO<>(toDTO(pets.getContent()), pets.getTotalElements(), pets.getTotalPages());
    }
}
