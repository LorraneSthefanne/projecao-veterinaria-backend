package br.com.lorrane.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author renanravelli
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginaDTO<T> {

    private List<T> lista;
    private long quantidade;
    private int totalPagina;
}
