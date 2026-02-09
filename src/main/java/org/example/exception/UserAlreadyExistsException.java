package org.example.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
        super("User with the email '" + email + "' already exists.");
    }
}
