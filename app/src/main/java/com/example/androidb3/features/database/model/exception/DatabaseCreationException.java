package com.example.androidb3.features.database.model.exception;

public class DatabaseCreationException extends Exception{
    public DatabaseCreationException(String message){
        super(message);
    }

    public DatabaseCreationException(String message, Throwable cause){
        super(message, cause);
    }
}
