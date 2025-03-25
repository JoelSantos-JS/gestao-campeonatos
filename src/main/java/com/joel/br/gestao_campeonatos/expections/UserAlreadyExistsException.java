package com.joel.br.gestao_campeonatos.expections;

public class UserAlreadyExistsException extends RuntimeException{



    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }


}
