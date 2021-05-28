package br.com.lorrane.controllers.dto.novo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetNovoDTO {

    @NotNull(message = "ID da pessoa n\u00E3o pode ser nulo.")
    private UUID idPessoa;
    @NotNull(message = "Nome n\u00E3o pode ser nulo.")
    private String nome;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Data de nascimento n\u00E3o pode ser nulo.")
    private LocalDate dataNascimento;
    @NotNull(message = "Ra\u00E7a n\u00E3o pode ser nulo.")
    private String raca;
    @NotNull(message = "Esp\u00E9cie n\u00E3o pode ser nulo.")
    private String especie;
    @NotNull(message = "Peso n\u00E3o pode ser nulo.")
    private Double peso;
}
