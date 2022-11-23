package com.example.Recipe.API.exceptions;

public class NoSuchRecipeException extends RuntimeException{

    public NoSuchRecipeException (String message) {
        super(message);
    }
    public NoSuchRecipeException() {
    }

}
