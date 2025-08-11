package com.example.androidb3.features.database.model.exception;

public class DaoOperationException extends Exception{
  public DaoOperationException(String message){
    super(message);
  }

  public DaoOperationException(String message, Throwable cause){
    super(message, cause);
  }
}
