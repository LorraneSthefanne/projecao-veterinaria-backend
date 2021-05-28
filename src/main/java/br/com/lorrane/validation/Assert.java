package br.com.lorrane.validation;

import br.com.lorrane.exceptions.BusinessException;
import br.com.lorrane.exceptions.Mensagem;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@NoArgsConstructor
public class Assert {

    public static void isTrue(boolean condition, Mensagem Mensagem, Object... messageParams) {
        if (!condition) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isFalse(boolean condition, Mensagem Mensagem, Object... messageParams) {
        if (condition) {
            throw new BusinessException(Mensagem);
        }
    }

    public static void isNull(Object value, Mensagem Mensagem, Object... messageParams) {
        if (value != null) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isNotNull(Object value, Mensagem Mensagem, Object... messageParams) {
        if (value == null) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isEmpty(String value, Mensagem Mensagem, Object... messageParams) {
        if (value != null && value.trim().length() > 0) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isEmpty(Collection<?> value, Mensagem Mensagem, Object... messageParams) {
        if (value != null && !value.isEmpty()) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isEmpty(Map<?, ?> value, Mensagem Mensagem, Object... messageParams) {
        if (value != null && !value.isEmpty()) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static <T> void isEmpty(T[] value, Mensagem Mensagem, Object... messageParams) {
        if (value != null && value.length > 0) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isNotEmpty(String value, Mensagem Mensagem, Object... messageParams) {
        if (value == null || value.trim().length() == 0) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isNotEmpty(Collection<?> value, Mensagem Mensagem, Object... messageParams) {
        isNotNull(value, Mensagem, messageParams);
        if (value.isEmpty()) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isNotEmpty(Map<?, ?> value, Mensagem Mensagem, Object... messageParams) {
        isNotNull(value, Mensagem, messageParams);
        if (value.isEmpty()) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static <T> void isNotEmpty(T[] value, Mensagem Mensagem, Object... messageParams) {
        isNotNull(value, Mensagem, messageParams);
        if (value.length == 0) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isAfter(LocalDateTime first, LocalDateTime other, Mensagem Mensagem, Object... messageParams) {
        isNotNull(first, Mensagem, messageParams);
        isNotNull(other, Mensagem, messageParams);
        if (!first.isAfter(other)) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isAfter(LocalDate first, LocalDate other, Mensagem Mensagem, Object... messageParams) {
        isNotNull(first, Mensagem, messageParams);
        isNotNull(other, Mensagem, messageParams);
        if (!first.isAfter(other)) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isAfterOrEquals(LocalDateTime first, LocalDateTime other, Mensagem Mensagem, Object... messageParams) {
        isNotNull(first, Mensagem, messageParams);
        isNotNull(other, Mensagem, messageParams);
        if (!first.isAfter(other) && !first.equals(other)) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }

    public static void isAfterOrEquals(LocalDate first, LocalDate other, Mensagem Mensagem, Object... messageParams) {
        isNotNull(first, Mensagem, messageParams);
        isNotNull(other, Mensagem, messageParams);
        if (!first.isAfter(other) && !first.equals(other)) {
            throw new BusinessException(Mensagem, messageParams);
        }
    }
}
