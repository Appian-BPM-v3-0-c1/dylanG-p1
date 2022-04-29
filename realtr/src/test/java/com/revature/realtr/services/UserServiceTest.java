package com.revature.realtr.services;

import com.revature.realtr.daos.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    UserService userService = new UserService(new UserDAO());

    @Test
    void unIsValid() {
        Assertions.assertFalse(userService.unIsValid("123"));
        Assertions.assertFalse(userService.unIsValid("dylan"));
        Assertions.assertTrue(userService.unIsValid("captainawesome"));
        Assertions.assertFalse(userService.unIsValid(""));
        Assertions.assertFalse(userService.unIsValid("!@#$%^^&&@#$"));

        Assertions.assertTrue(userService.unIsDuplicate("testuser"));
        Assertions.assertFalse(userService.unIsDuplicate("uniqueusername"));
    }

    @Test
    void pwIsValid() {
        Assertions.assertFalse(userService.pwIsValid("123"));
        Assertions.assertFalse(userService.pwIsValid("password"));
        Assertions.assertFalse(userService.pwIsValid("!@#$%^&*"));
        Assertions.assertFalse(userService.pwIsValid(""));
        Assertions.assertTrue(userService.pwIsValid("password11!!"));
    }
}