package br.com.lorrane.handlers;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CustomErrors {

    private int errorCode;
    private String error;
    private String errorMessage;
    private List<? extends Object> fieldErrors = new ArrayList<>();

    @Builder
    public CustomErrors(HttpStatus status, String message, List<? extends Object> fieldErrors) {
        this.errorCode = status.value();
        this.error = status.name();
        this.errorMessage = message;
        this.fieldErrors = fieldErrors;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<?> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
