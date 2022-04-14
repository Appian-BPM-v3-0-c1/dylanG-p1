package com.revature.realtr.services;

import com.revature.realtr.daos.UserDAO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService = new UserService(new UserDAO());

    @Test
    void unIsValid() {
        Assertions.assertEquals(false, userService.unIsValid("123"));
        Assertions.assertEquals(false, userService.unIsValid("dylan"));
        Assertions.assertEquals(true, userService.unIsValid("dylangierok"));
        Assertions.assertEquals(false, userService.unIsValid(""));
        Assertions.assertEquals(false, userService.unIsValid("!@#$%^^&&@#$"));
    }

    @Test
    void pwIsValid() {
        Assertions.assertEquals(false, userService.pwIsValid("123"));
        Assertions.assertEquals(false, userService.pwIsValid("password"));
        Assertions.assertEquals(false, userService.pwIsValid("!@#$%^&*"));
        Assertions.assertEquals(false, userService.pwIsValid(""));
        Assertions.assertEquals(true, userService.pwIsValid("password11!!"));
    }
}