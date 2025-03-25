package com.joel.br.gestao_campeonatos.expections;

public class EntityAlreadyExistsException extends RuntimeException{

    public EntityAlreadyExistsException (String message) {
        super(message);
    }

    public EntityAlreadyExistsException (String message, Throwable throwable) {
        super(message, throwable);
    }
}
