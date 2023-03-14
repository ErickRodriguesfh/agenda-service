package br.ebr.agenda.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
@Getter
public class NegocioException extends RuntimeException{

    public NegocioException(String message) {
        super(message);
    }

}