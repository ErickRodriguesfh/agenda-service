package br.ebr.agenda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NegocioException.class)
    public ResponseEntity<ErrorResponse> handlerNegocioException(NegocioException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handlerEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}