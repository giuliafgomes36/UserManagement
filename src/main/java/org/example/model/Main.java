package org.example.model;

import org.example.exception.InvalidUserEmailException;
import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserDoesNotExistsException;
import org.example.exception.InvalidUserNameException;
import org.example.service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
    System.out.println("\u001B[42m" + "WELCOME TO USER MANAGEMENT" + "\u001B[0m");
        var runProgram = "run";
        UserService userService = new UserService();

        while(runProgram.equals("run"))
        {
            try {
                Scanner scanner = new Scanner(System.in);

                printActions();
                var input = scanner.next();

                switch(input) {
                    case "1":
                        addUser(userService, scanner);
                        break;
                    case "2":
                        updateUserName(userService, scanner);
                        break;
                    case "3":
                        updateUserAge(userService, scanner);
                        break;
                    case "4":
                        deleteUser(userService, scanner);
                        break;
                    case "5":
                        printUserList(userService);
                        break;
                    case "6":
                        runProgram = "stop";
                        printServiceTitle("Bye! ;)");
                        break;
                    default:
                        printErrorMessage("Select a valid option");
                }
            } catch (UserAlreadyExistsException | UserDoesNotExistsException | InvalidUserNameException |
                     InvalidUserEmailException exception) {
                printErrorMessage(exception.getMessage());
            } catch (InputMismatchException inputMismatchException) {
                printErrorMessage("Invalid input, please try again.");
            }
        }
    }

    private static void printActions() {
        System.out.println("[1]: Add user");
        System.out.println("[2]: Update user name");
        System.out.println("[3]: Update user age");
        System.out.println("[4]: Delete user");
        System.out.println("[5]: Print user list");
        System.out.println("[6]: Stop program");
        System.out.print("Choose service: ");
    }

    private static void addUser(UserService userService, Scanner scanner) {
        printServiceTitle("Add User Service");

        User user = new User();
        System.out.print("Inform user name: ");
        user.setName(scanner.next());
        System.out.print("Inform user age: ");
        user.setAge(scanner.nextInt());
        System.out.print("Inform user email: ");
        user.setEmail(scanner.next());

        userService.addUser(user);

        printServiceResponse("User added");
    }

    private static void updateUserName(UserService userService, Scanner scanner) {
        printServiceTitle("Update User Name Service");

        System.out.print("Inform email: ");
        var email = scanner.next();
        System.out.print("Inform new name: ");
        var name = scanner.next();

        userService.updateUserName(email, name);

        printServiceResponse("Name updated");
    }

    private static void updateUserAge(UserService userService, Scanner scanner) {
        printServiceTitle("Update User Age Service");

        System.out.print("Inform email: ");
        var email = scanner.next();
        System.out.print("Inform new age: ");
        var age = scanner.nextInt();

        userService.updateUserAge(email, age);

        printServiceResponse("Age updated");
    }

    private static void deleteUser(UserService userService, Scanner scanner) {
        printServiceTitle("Delete User Service");

        System.out.print("Inform email: ");
        userService.deleteUser(scanner.next());

        printServiceResponse("User deleted");
    }

    private static void printUserList(UserService userService) {
        printServiceTitle("User List");
        userService.printUserList();
    }

    private static void printServiceTitle(String title) {
        System.out.println("\u001B[34m" + title + "\u001B[0m");
    }

    private static void printServiceResponse(String response) {
        System.out.println("\u001B[32m" + response + "\u001B[0m");
    }

    private static void printErrorMessage(String errorMessage) {
        System.out.println("\u001B[31m" + errorMessage + "\u001B[0m");
    }
}
