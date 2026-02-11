package org.example.service;

import org.example.exception.InvalidUserEmailException;
import org.example.exception.InvalidUserNameException;
import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserDoesNotExistsException;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserService userService;

    @BeforeEach
     void setUp() {
        userService = new UserService();
        userService.setUserList(new ArrayList<>());
    }

    @Test
    void shouldAddUserWhenEmailDoesNotExist() {
        userService.addUser(createUser("Test", "email@gmail.com", 25));
        userService.addUser(createUser("Test2", "email2@gmail.com", 26));
        assertEquals(2, userService.getUserList().size());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExist() {
        userService.addUser(createUser("Test", "email@gmail.com", 25));
        assertThrows(
                UserAlreadyExistsException.class,
                () -> userService.addUser(createUser("Test2", "email@gmail.com", 26))
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(
                InvalidUserNameException.class,
                () -> userService.addUser(createUser(null, "email@gmail.com", 26))
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        assertThrows(
                InvalidUserNameException.class,
                () -> userService.addUser(createUser("", "email@gmail.com", 26))
        );
    }

    @Test
    void shouldDeleteWhenUserEmailIsFound() {
        userService.addUser(createUser("Test", "email1@gmail.com", 25));
        userService.addUser(createUser("Test", "email2@gmail.com", 25));

        userService.deleteUser("email1@gmail.com");

        assertTrue(userService.getUserList().stream().noneMatch(x -> x.getEmail().equals("email1@gmail.com")));
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExistAtDelete() {
        userService.addUser(createUser("Test", "email@gmail.com", 25));
        assertThrows(
                UserDoesNotExistsException.class,
                () -> userService.deleteUser("email.inexistent@gmail.com")
        );
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalidAtDelete() {
        assertThrows(
                InvalidUserEmailException.class,
                () -> userService.deleteUser("email.invalid.com")
        );
    }

    @Test
    void shouldUpdateUserNameWhenEmailIsFound() {
        userService.addUser(createUser("Test", "email@gmail.com", 25));
        userService.updateUserName("email@gmail.com", "NewName");

        assertTrue(userService.getUserList().stream().anyMatch(x -> x.getName().equals("NewName")));
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExistAtNameUpdate() {
        userService.addUser(createUser("Test", "email@gmail.com", 25));
        assertThrows(
                UserDoesNotExistsException.class,
                () -> userService.updateUserName("email.inexistent@gmail.com", "NewName")
        );
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalidAtNameUpdate() {
        assertThrows(
                InvalidUserEmailException.class,
                () -> userService.updateUserName("email.invalid.com", "NewName")
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsNullAtNameUpdate() {
        assertThrows(
                InvalidUserNameException.class,
                () -> userService.updateUserName("email@gmail.com", null)
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmptyAtNameUpdate() {
        assertThrows(
                InvalidUserNameException.class,
                () -> userService.updateUserName("email@gmail.com", "")
        );
    }

    @Test
    void shouldUpdateUserAgeWhenEmailIsFound() {
        userService.addUser(createUser("Test", "email@gmail.com", 25));
        userService.updateUserAge("email@gmail.com", 26);

        assertTrue(userService.getUserList().stream().anyMatch(x -> x.getAge() == 26));
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExistAtAgeUpdate() {
        userService.addUser(createUser("Test", "email@gmail.com", 25));

        assertThrows(
                UserDoesNotExistsException.class,
                () -> userService.updateUserAge("email.inexistent@gmail.com", 26)
        );
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalidAtAgeUpdate() {
        assertThrows(
                InvalidUserEmailException.class,
                () -> userService.updateUserAge("email.invalid.com", 30)
        );
    }

    User createUser(String name, String email, int age) {
        return new User(name, email, age);
    }
}
