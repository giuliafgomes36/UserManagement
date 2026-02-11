package org.example.exception;

public class InvalidUserNameException extends RuntimeException {
    public InvalidUserNameException() {
        super("User name is invalid.");
    }
}
