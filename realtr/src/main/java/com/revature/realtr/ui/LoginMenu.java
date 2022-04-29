package com.revature.realtr.ui;

import com.revature.realtr.daos.*;
import com.revature.realtr.models.User;
import com.revature.realtr.services.*;
import com.revature.realtr.util.UserDNEException;

import java.util.Locale;
import java.util.Scanner;

public class LoginMenu implements iMenu {
    private final UserService userService;

    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    User user = new User();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        char userInput = ' ';
        boolean booEx = false;

        while (!booEx) {

            System.out.println("Please choose from the following options:\n");
            System.out.println("[1] Login to Existing Account");
            System.out.println("[2] Create New Account");
            System.out.println("[3] Return to Main Menu");
            System.out.println();
            System.out.print("Selection: ");

            userInput = scanner.next().charAt(0);

            switch (userInput) {
                case '1':
                    login();
                    break;
                case '2':
                    createAccount();
                    break;
                case '3':
                    new MainMenu().start();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid menu entry. Please try again.\n");
                    break;
            }
        }
    }

    private void createAccount() {

        System.out.println("\n--- Create New User ---");

        String username = "";
        String password1 = "";
        String password2 = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String address = "";
        String city = "";
        String state = "";
        String zip = "";

        while (true) {
            while (true) {
                System.out.print("Please enter a username: ");
                username = scanner.next();

                if (!userService.unIsDuplicate(username)) {
                    if (userService.unIsValid(username)) {
                        user.setUsername(username);
                        break;
                    } else {
                        System.out.println("Invalid username. Please try again.");
                    }
                } else {
                    System.out.println("Username already exists. Please try again.");
                }
            }

            while (true) {
                System.out.print("\nPlease enter a password: ");
                password1 = scanner.next();
                System.out.print("\nPlease re-renter your password: ");
                password2 = scanner.next();

                if (password1.equals(password2)) {
                    if (userService.pwIsValid(password1)) {
                        user.setPassword(password1);
                        break;
                    } else {
                        System.out.println("Invalid password. Please try again.");
                    }
                } else {
                    System.out.println("Passwords do not match. Please try again.");
                }
            }

            System.out.print("\nFirst Name: ");
            firstName = scanner.next();
            user.setFirstName(firstName);

            System.out.print("\nLast Name: ");
            lastName = scanner.next();
            user.setLastName(lastName);

            System.out.print("\nEmail: ");
            email = scanner.next();
            user.setEmail(email);

            System.out.print("\nAddress: ");
            address = scanner.next();
            user.setAddress(address);

            System.out.print("\nCity: ");
            city = scanner.next();
            user.setCity(city);

            System.out.print("\nState (abbreviations only): ");
            state = scanner.next().toUpperCase(Locale.ROOT);

            if (state.length() == 2) {
                user.setState(state);
            } else {
                System.out.println("Invalid state. Please try again.");
                break;
            }

            System.out.print("\nZip Code: ");
            zip = scanner.next();
            if (zip.length() == 5) {
                user.setZip(zip);
            } else {
                System.out.println("Invalid zip code. Please try again.\n");
                break;
            }

            user.setUsertype("default");

            System.out.println("\nPlease confirm your details.");
            System.out.println("\nUsername: " + username);
            System.out.println("Password: " + password1);
            System.out.println("Name: " + firstName + " " + lastName);
            System.out.println("Email: " + email);
            System.out.println("Address: " + address);
            System.out.println("City: " + city);
            System.out.println("State: " + state);
            System.out.println("Zip: " + zip);
            System.out.print("\nIs this correct? (y/n) ");

            if (scanner.next().charAt(0) == 'y') {
                userService.getUserDAO().save(user);
                System.out.println("Account created. Welcome to plume, " + firstName);
                break;
            }
        }
    }

    private void login() {

        while (true) {
            System.out.print("\nUsername: ");
            user.setUsername(scanner.next());
            System.out.print("\nPassword: ");
            user.setPassword(scanner.next());

            try {
                if (!userService.loginIsValid(user)) {
                    throw new UserDNEException();
                }
            } catch (UserDNEException e) {
                System.out.println("This is a Custom Exception displaying you have entered an invalid login. Please try again.");
            }

            if (userService.loginIsValid(user)) {
                user = userService.getUserDAO().findByUsername(user.getUsername());

                if (user.getUsertype().equals("default")) {
                    System.out.println("\nLogin accepted...");
                    new PenMenu(user, new PenService(new PenDAO()), new CartService(new CartDAO()), new HistoryService(new HistoryDAO())).start();
                    break;

                } else if (user.getUsertype().equals("manager")) {
                    new ManagerMenu(user, new PenService(new PenDAO()), new HistoryService(new HistoryDAO()), new UserService(new UserDAO()), new LocationService(new LocationDAO())).start();
                    break;
                }
            }
        }
    }
}
