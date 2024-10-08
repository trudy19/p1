package com.example.hinking.exceptions;
/**
 * Cette exception est levée lorsqu'un utilisateur essaie d'accéder à une ressource ou
 * d'effectuer une action pour laquelle
 * il n'a pas les autorisations appropriées (non authentifié ou non autorisé).
 */
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
