package com.revature.realtr.util;

public class CartIsEmptyException extends Exception {

    public CartIsEmptyException() {

    }

    public CartIsEmptyException(String eMsg) {
        super(eMsg);
    }

}
