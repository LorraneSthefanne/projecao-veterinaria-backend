package br.com.lorrane.exceptions;

import java.util.function.Supplier;

public class BusinessException extends RuntimeException {

    private Mensagem mensagem;
    private Object[] params;

    public BusinessException(Mensagem mensagem) {
        super(mensagem.getMensagem());
    }

    public BusinessException(Mensagem mensagem, Object... params) {
        this(null, mensagem, params);
    }

    public BusinessException(Throwable cause, Mensagem mensagem, Object... params) {
        super(mensagem.getMensagem(), cause);
        this.mensagem = mensagem;
        this.params = params;
    }

    public static Supplier<BusinessException> supplier(Mensagem mensagem) {
        return () -> new BusinessException(mensagem);
    }
}
