package uz.com.ecommers.exception;

public class DataHasAlreadyExistException extends RuntimeException {
    public DataHasAlreadyExistException(String message){
        super(message);
    }
}
