package uz.com.ecommers.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException(String message){super(message);}
}
