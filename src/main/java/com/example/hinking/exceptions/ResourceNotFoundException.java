package com.example.hinking.exceptions;


/*
 * Cette exception est utilisée lorsqu' le donnée ne se trouve pas .
 * Par exemple, si l'utilisateur essaie de recuper  une ressource qui n'
 * n'existe pas ,
 * */

public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(resourceName + " not found with id: " + resourceId);
    }

    public ResourceNotFoundException(String resourceName, String identifier) {
        super(resourceName + " not found with identifier: " + identifier);
    }
}
