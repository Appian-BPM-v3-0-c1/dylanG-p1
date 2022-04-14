package com.revature.realtr;

import com.revature.realtr.connection.DatabaseConnection;
import com.revature.realtr.ui.MainMenu;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        new MainMenu().start();
//        Connection con = DatabaseConnection.getCon();
//        System.out.println(con);


    }
}
