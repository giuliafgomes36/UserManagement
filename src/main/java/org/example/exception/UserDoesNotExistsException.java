package org.example.exception;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(String email)
    {
        super("User with the email '" + email + "' does not exist.");
    }
}
