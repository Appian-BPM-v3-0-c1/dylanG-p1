package com.revature.realtr.services;

import com.revature.realtr.daos.PenDAO;

public class PenService {
    private final PenDAO penDAO;

    public PenService(PenDAO penDAO) {
        this.penDAO = penDAO;
    }

    public PenDAO getPenDAO() {
        return penDAO;
    }
}
