package maxm.androidb3.androidb3.features.year.model.exception;

public class EmptyDialogInputException extends Exception{
  public EmptyDialogInputException(String message){
    super(message);
  }

  public EmptyDialogInputException(String message, Throwable cause){
    super(message, cause);
  }
}
