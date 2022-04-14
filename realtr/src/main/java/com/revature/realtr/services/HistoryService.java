package com.revature.realtr.services;

import com.revature.realtr.daos.HistoryDAO;

public class HistoryService {
    private final HistoryDAO historyDAO;


    public HistoryService(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    public HistoryDAO getHistoryDAO() {
        return historyDAO;
    }
}
