package org.example.service;

import org.example.exception.UserAlreadyExistsException;
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

    User createUser(String name, String email, int age) {
        return new User(name, email, age);
    }
}
