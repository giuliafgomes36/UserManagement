package org.example.service;

import org.example.exception.UserAlreadyExistsException;
import org.example.model.User;

import java.util.List;

public class UserService {

    private static List<User> userList;

    public void addUser(User user) {
        if(checkIfUserAlreadyExists(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        } else {
            userList.add(user);
        }
    }

    //true: user exists, false -> user does not exist.
    public boolean checkIfUserAlreadyExists(String email) {
        var user = userList.stream().filter(x -> x.getEmail().equals(email)).toList();

        if(user.isEmpty()) { return false; };

        return true;
    }
}
