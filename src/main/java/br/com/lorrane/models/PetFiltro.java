package br.com.lorrane.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetFiltro {

    @NotNull(message = "ID da pessoa n\u00E3o pode ser nulo.")
    private UUID idPessoa;
    private String nome;
}
