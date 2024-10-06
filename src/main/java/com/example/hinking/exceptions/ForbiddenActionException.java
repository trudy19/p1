package com.example.hinking.exceptions;
/**
 * Cette exception est levée lorsqu'un utilisateur essaie d'accéder à une ressource ou
 * d'effectuer une action pour laquelle
 * il n'a pas les autorisations appropriées (non autorisé ).
 * ForbiddenActionException : Quand un utilisateur authentifié tente d'effectuer
 * une action pour laquelle il n'a pas les permissions.
 */
public class ForbiddenActionException extends RuntimeException {
    public ForbiddenActionException(String message) {
        super(message);
    }
}
