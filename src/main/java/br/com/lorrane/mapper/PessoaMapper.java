package br.com.lorrane.mapper;

import br.com.lorrane.controllers.dto.PaginaDTO;
import br.com.lorrane.controllers.dto.PessoaDTO;
import br.com.lorrane.controllers.dto.novo.PessoaNovoDTO;
import br.com.lorrane.entities.Pessoa;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface PessoaMapper {

    public Pessoa fromDTO(PessoaNovoDTO novoDTO);

    public Pessoa fromDTO(PessoaDTO dto);

    public PessoaDTO toDTO(Pessoa pessoa);

    public List<PessoaDTO> toDTO(List<Pessoa> pessoas);

    public default PaginaDTO<PessoaDTO> toDTO(Page<Pessoa> pessoas) {
        return new PaginaDTO<>(toDTO(pessoas.getContent()), pessoas.getTotalElements(), pessoas.getTotalPages());
    }
}
