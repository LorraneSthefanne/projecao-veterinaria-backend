package br.com.lorrane.exceptions;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }


    public static Supplier<NotFoundException> supplier(Mensagem mensagem) {
        return () -> new NotFoundException(mensagem.getMensagem());
    }
}
