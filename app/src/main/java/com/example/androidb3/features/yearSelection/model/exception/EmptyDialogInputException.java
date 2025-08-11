package com.example.androidb3.features.yearSelection.model.exception;

public class EmptyDialogInputException extends Exception{
  public EmptyDialogInputException(String message){
    super(message);
  }

  public EmptyDialogInputException(String message, Throwable cause){
    super(message, cause);
  }
}
