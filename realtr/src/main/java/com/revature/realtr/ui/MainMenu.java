package com.revature.realtr.ui;

import com.revature.realtr.daos.UserDAO;
import com.revature.realtr.services.UserService;

import java.util.Scanner;

public class MainMenu implements iMenu{

    @Override
    public void start() {



        char userInput = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean booEx = false;
        System.out.println("             ,dPYb,");
        System.out.println("             IP'`Yb");
        System.out.println("             I8  8I");
        System.out.println("             I8  8'");
        System.out.println(" gg,gggg,    I8 dP  gg      gg   ,ggg,,ggg,,ggg,    ,ggg,");
        System.out.println(" I8P\"  \"Yb   I8dP   I8      8I  ,8\" \"8P\" \"8P\" \"8,  i8\" \"8i");
        System.out.println(" I8'    ,8i  I8P    I8,    ,8I  I8   8I   8I   8I  I8, ,8I");
        System.out.println(",I8 _  ,d8' ,d8b,_ ,d8b,  ,d8b,,dP   8I   8I   Yb, `YbadP'");
        System.out.println("PI8 YY88888P8P'\"Y888P'\"Y88P\"`Y88P'   8I   8I   `Y8888P\"Y888");
        System.out.println(" I8");
        System.out.println(" I8");
        System.out.println(" I8");
        System.out.println(" I8\n");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("|   The Best Application You'll Ever Use to Buy Fake Fountain Pens  |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println();

        while (!booEx) {

            System.out.println("Please choose from the following options:\n");

            System.out.println("[1] Login or Create New Account");
            System.out.println("[2] Exit");
            System.out.println();
            System.out.print("Selection: ");
            userInput = scanner.next().charAt(0);

            switch (userInput) {
                case '1':
                    new LoginMenu(new UserService(new UserDAO())).start();

                    break;
                case '2':
                    booEx = true;
                    break;
                default:
                    System.out.println("\nInvalid menu entry. Please try again.\n");
                    break;
            }
        }
    }
}
