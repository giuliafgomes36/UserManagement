package org.example.service;

import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserDoesNotExistsException;
import org.example.model.User;

import java.util.List;

public class UserService {

    private static List<User> userList;

    public void addUser(User user) {
        if(checkIfUserExists(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        } else {
            userList.add(user);
        }
    }

    public void deleteUser(User user) {
        if(checkIfUserExists(user.getEmail())) {
            userList.remove(user);
        } else {
            throw new UserDoesNotExistsException(user.getEmail());
        }
    }

    public void updateUserName(String email, String newName) {
        var userToBeUpdated = userList.stream()
                .filter(x -> x.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new UserDoesNotExistsException(email));

        userToBeUpdated.setName(newName);
    }

    //true: user exists, false: user does not exist.
    private boolean checkIfUserExists(String email) {
        return userList.stream().anyMatch(x -> x.getEmail().equalsIgnoreCase(email));
    }
}
