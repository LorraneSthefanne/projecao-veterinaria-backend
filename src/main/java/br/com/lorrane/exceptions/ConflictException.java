package br.com.lorrane.exceptions;

import java.util.function.Supplier;

/**
 * @author renanravelli
 */
public class ConflictException extends RuntimeException {
    public ConflictException() {
    }

    public ConflictException(Mensagem message) {
        super(message.getMensagem());
    }


    public static Supplier<ConflictException> supplier(Mensagem mensagem) {
        return () -> new ConflictException(mensagem);
    }
}
