package br.com.lorrane.services.impl;

import br.com.lorrane.entities.Pessoa;
import br.com.lorrane.entities.QPessoa;
import br.com.lorrane.exceptions.Mensagem;
import br.com.lorrane.exceptions.NotFoundException;
import br.com.lorrane.expression.BooleanExpressionBuilder;
import br.com.lorrane.models.PessoaFiltro;
import br.com.lorrane.repositories.PessoaRepository;
import br.com.lorrane.services.PessoaService;
import br.com.lorrane.validation.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    @Override
    @Transactional
    public Pessoa cadastrar(Pessoa pessoa) {
        Boolean existeEmail = pessoaRepository.existsByEmail(pessoa.getEmail());
        Assert.isFalse(existeEmail, Mensagem.EMAIL_JA_CADASTRADO);
        Boolean existeDocumento = pessoaRepository.existsByDocumento(pessoa.getDocumento());
        Assert.isFalse(existeDocumento, Mensagem.DOCUMENTO_JA_CADASTRADO);
        return pessoaRepository.save(pessoa);
    }

    @Override
    @Transactional
    public Pessoa atualizar(UUID id, Pessoa pessoa) {
        Boolean existeEmail = pessoaRepository.existsByEmailAndIdIsNot(pessoa.getEmail(), id);
        Assert.isFalse(existeEmail, Mensagem.EMAIL_JA_CADASTRADO);
        Boolean existeDocumento = pessoaRepository.existsByDocumentoAndIdIsNot(pessoa.getDocumento(), id);
        Assert.isFalse(existeDocumento, Mensagem.DOCUMENTO_JA_CADASTRADO);

        Pessoa pessoaEncontrada = buscar(id);
        pessoaEncontrada.setDataNascimento(pessoa.getDataNascimento());
        pessoaEncontrada.setDocumento(pessoa.getDocumento());
        pessoaEncontrada.setEmail(pessoa.getEmail());
        pessoaEncontrada.setNome(pessoa.getNome());
        pessoaEncontrada.setTelefone(pessoa.getTelefone());
        pessoaEncontrada.setPets(pessoa.getPets());
        return pessoaRepository.save(pessoaEncontrada);
    }

    @Override
    public Pessoa buscar(UUID id) {
        return pessoaRepository.findById(id)
                .orElseThrow(NotFoundException.supplier(Mensagem.PESSOA_NAO_ENCONTRADA));
    }

    @Override
    public Page<Pessoa> buscar(PessoaFiltro filtro, int pagina, int tamanho) {
        QPessoa PESSOA = QPessoa.pessoa;
        var expression = new BooleanExpressionBuilder(PESSOA.isNotNull())
                .notNullAnd(PESSOA.nome::startsWithIgnoreCase, filtro.getNome())
                .notNullAnd(PESSOA.documento::eq, filtro.getDocumento())
                .notNullAnd(PESSOA.email::eq, filtro.getEmail())
                .build();
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        return pessoaRepository.findAll(expression, pageRequest);
    }

    @Override
    @Transactional
    public void remover(UUID id) {
        Pessoa pessoaEncontrada = buscar(id);
        pessoaRepository.delete(pessoaEncontrada);
    }
}
