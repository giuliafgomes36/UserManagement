package org.example.service;

import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserDoesNotExistsException;
import org.example.model.User;
import org.example.validation.UserValidator;

import java.util.ArrayList;

public class UserService {

    private ArrayList<User> userList = new ArrayList<>();

    public void addUser(User user) {
        var validator = new UserValidator();
        validator.validateNewUser(user);

        if(checkIfUserExists(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        } else {
            userList.add(user);
        }
    }

    public void updateUserName(String email, String newName) {
        var validator = new UserValidator();
        validator.validateUserNameAndEmail(newName, email);

        var userToBeUpdated = findUserWithEmail(email);
        userToBeUpdated.setName(newName);
    }

    public void updateUserAge(String email, int age) {
        var validator = new UserValidator();
        validator.validateEmail(email);

        var userToBeUpdated = findUserWithEmail(email);
        userToBeUpdated.setAge(age);
    }

    public void deleteUser(String email) {
        var validator = new UserValidator();
        validator.validateEmail(email);

        var userToBeDeleted = findUserWithEmail(email);
        userList.remove(userToBeDeleted);
    }

    public void printUserList() {
        if(userList.isEmpty()) {
            System.out.println("\u001B[33m" + "No users" + "\u001B[0m");
        } else {
            userList.forEach(x ->
                    System.out.println(
                            "\u001B[33m"
                            + "Name: " + x.getName()
                            + " Email: " + x.getEmail()
                            + " Age: " + x.getAge()
                            + "\u001B[0m"
                    ));
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    private boolean checkIfUserExists(String email) {
        return userList.stream().anyMatch(x -> x.getEmail().equalsIgnoreCase(email));
    }

    private User findUserWithEmail(String email) {
        return userList.stream()
                .filter(x -> x.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new UserDoesNotExistsException(email));
    }
}
