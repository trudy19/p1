package com.example.hinking.exceptions;



/// Cette exception peut être levée lorsque les donnée envoyer n'est pas validé .
/// Cela pourrait inclure des erreurs de validation des paramètres,
///
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}