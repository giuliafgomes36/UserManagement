package org.example.exception;

public class InvalidUserEmailException extends RuntimeException {
    public InvalidUserEmailException() {
        super("The email provided must be valid.");
    }
}
