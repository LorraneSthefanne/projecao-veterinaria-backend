package br.com.lorrane.services;

import br.com.lorrane.entities.Pessoa;
import br.com.lorrane.models.PessoaFiltro;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PessoaService {

    Pessoa cadastrar(Pessoa pessoa);

    Pessoa atualizar(UUID id, Pessoa pessoa);

    Pessoa buscar(UUID id);

    Page<Pessoa> buscar(PessoaFiltro filtro, int pagina, int tamanho);

    void remover(UUID id);
}
