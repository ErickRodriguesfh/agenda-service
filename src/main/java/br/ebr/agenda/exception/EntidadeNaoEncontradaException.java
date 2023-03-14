package br.ebr.agenda.exception;


@SuppressWarnings("serial")
public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

}