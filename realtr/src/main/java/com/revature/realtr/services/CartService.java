package com.revature.realtr.services;

import com.revature.realtr.daos.CartDAO;

public class CartService {
    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public CartDAO getCartDAO() {
        return cartDAO;
    }
}
