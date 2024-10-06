package com.example.hinking.exceptions;

/// Cette exception peut être levée lorsque le client envoie des données invalides
///  ou incomplètes dans une requête. Cela pourrait inclure des erreurs de validation des paramètres,
/// des erreurs dans le format des données envoyées, etc.
///
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}