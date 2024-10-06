package com.example.hinking.exceptions;



/*
* Cette exception est utilisée lorsqu'il y a un conflit de données.
* Par exemple, si l'utilisateur essaie de créer une ressource qui existe déjà (comme un doublon),
* ou si une opération entre en conflit avec une autre.
* */

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}