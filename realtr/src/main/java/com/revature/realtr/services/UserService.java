package com.revature.realtr.services;

import com.revature.realtr.daos.UserDAO;
import com.revature.realtr.models.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public boolean unIsDuplicate(String username) {
        List<String> usernameList = userDAO.findAllUsernames();

        for (String unL : usernameList) {
            if (unL.equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }

    public boolean unIsValid(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean pwIsValid(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    public boolean loginIsValid(User user) {
        List<User> userList = userDAO.findAll();

        for (User u : userList) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }
}
