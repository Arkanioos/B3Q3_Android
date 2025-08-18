package maxm.androidb3.androidb3.features.note.model.exception;

public class AlreadyExistingException extends RuntimeException {
    public AlreadyExistingException(String message){
        super(message);
    }

    public AlreadyExistingException(String message, Throwable cause){
        super(message, cause);
    }
}
