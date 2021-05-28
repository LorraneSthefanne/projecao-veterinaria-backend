package br.com.lorrane.handlers;

import br.com.lorrane.exceptions.BusinessException;
import br.com.lorrane.exceptions.ConflictException;
import br.com.lorrane.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERRO_INTERNO = "Ocorreu um erro interno no servidor: %s";

    @ResponseBody
    @ExceptionHandler(BindException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CustomErrors argumentNotValidException(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<Object> parametrosInvalidos = Arrays.asList(result.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toArray());
        return CustomErrors.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Ocorreu um erro na valida\u00E7\u00E3o das informa\u00E7\u00F5es.")
                .fieldErrors(parametrosInvalidos)
                .build();
    }

    @ResponseBody
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public CustomErrors handleConflictException(ConflictException exception) {
        log.error(exception.getMessage(), exception);
        return CustomErrors.builder()
                .status(HttpStatus.CONFLICT)
                .message(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public CustomErrors handleNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return CustomErrors.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CustomErrors handleNotFoundException(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return CustomErrors.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomErrors exception(Exception exception) {
        log.error(exception.getMessage(), exception);
        String msg = String.format(ERRO_INTERNO, exception.getMessage());
        return CustomErrors.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(msg)
                .build();
    }

}
