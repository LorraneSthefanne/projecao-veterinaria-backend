package br.com.lorrane.controllers.dto.novo;

import br.com.lorrane.controllers.dto.PetDTO;
import br.com.lorrane.entities.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaNovoDTO {

    private String nome;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String documento;
    private String telefone;
    private String email;
    private TipoPessoa tipo = TipoPessoa.TUTOR;
    private List<PetDTO> pets;
}
