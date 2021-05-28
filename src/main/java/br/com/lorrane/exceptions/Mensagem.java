package br.com.lorrane.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Mensagem {

    EMAIL_JA_CADASTRADO("E-mail j\u00E1 cadastrado."),
    DOCUMENTO_JA_CADASTRADO("Documento j\u00E1 cadastrado."),
    PESSOA_NAO_ENCONTRADA("Pessoa n\u00E3o encontrada."),
    USUARIO_NAO_ENCONTRADO("Usu\u00E1rio n\u00E3o encontrada."),
    USUARIO_JA_CADASTRADO("Usu\u00E1rio j\u00E1 cadastrado."),
    PET_NAO_ENCONTRADO("Pet n\u00E3o encontrado."),
    FREQUENCIA_JA_CONFIRMADA("Frequ\u00EAncia j\u00E1 confirmada.");

    private String mensagem;

}
