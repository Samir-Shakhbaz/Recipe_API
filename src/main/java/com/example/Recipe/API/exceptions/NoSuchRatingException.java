package com.example.Recipe.API.exceptions;

public class NoSuchRatingException extends Exception{
    public NoSuchRatingException(String message) {
        super(message);
    }
    public NoSuchRatingException() {
    }
}