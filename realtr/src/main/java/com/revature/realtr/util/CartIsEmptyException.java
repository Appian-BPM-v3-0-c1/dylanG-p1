package com.revature.realtr.util;

import com.revature.realtr.services.CartService;

public class CartIsEmptyException extends Exception {

    public CartIsEmptyException() {

    }

    public CartIsEmptyException (String eMsg) {
        super(eMsg);
    }

}
