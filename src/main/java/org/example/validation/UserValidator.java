package org.example.validation;

import org.example.exception.InvalidUserEmailException;
import org.example.exception.InvalidUserNameException;
import org.example.model.User;

public class UserValidator {

    public void validateNewUser(User user) {
        validateUserNameAndEmail(user.getName(), user.getEmail());
    }

    public void validateUserNameAndEmail(String name, String email) {
        validateName(name);
        validateEmail(email);
    }

    public void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidUserNameException();
        }
    }

    public void validateEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new InvalidUserEmailException();
        }
    }
}
