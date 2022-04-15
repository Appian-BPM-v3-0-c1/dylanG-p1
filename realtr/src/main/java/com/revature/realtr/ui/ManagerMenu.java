package com.revature.realtr.ui;

import com.revature.realtr.connection.DatabaseConnection;
import com.revature.realtr.models.History;
import com.revature.realtr.models.Location;
import com.revature.realtr.models.Pen;
import com.revature.realtr.models.User;
import com.revature.realtr.services.HistoryService;
import com.revature.realtr.services.LocationService;
import com.revature.realtr.services.PenService;
import com.revature.realtr.services.UserService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.*;

public class ManagerMenu implements iMenu {

    private final User user;
    private final PenService penService;
    private final HistoryService historyService;
    private final UserService userService;
    private final LocationService locService;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public ManagerMenu(User user, PenService penService, HistoryService historyService, UserService userService, LocationService locService) {
        this.user = user;
        this.penService = penService;
        this.historyService = historyService;
        this.userService = userService;
        this.locService = locService;
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        char userInput = ' ';
        boolean booEx = false;

        System.out.println("\nWelcome to the Manager Menu, " + user.getFirstName());

        while (!booEx) {
            System.out.println("\nPlease choose from the following options:\n");
            System.out.println("[1] View collection");
            System.out.println("[2] View order history by customer");
            System.out.println("[3] View order history by location");
            System.out.println("[4] View inventory by location");
            System.out.println("[5] Inventory quantity adjustment");
            System.out.println("[6] Add new pens to stock");
            System.out.println("[7] Show order histories by date");
            System.out.println("[8] Search customers by name");
            System.out.println("[9] Return to main menu\n");

            System.out.print("Selection: ");

            userInput = scanner.next().charAt(0);

            switch (userInput) {
                case '1':
                    showPens();
                    break;
                case '2':
                    showHistoryByCustomer();
                    break;
                case '3':
                    showHistoryByLocation();
                    break;
                case '4':
                    viewLocationInventory();
                    break;
                case '5':
                    invAdjust();
                    break;
                case '6':
                    addNewPen();
                    break;
                case '7':
                    sortByDate();
                    break;
                case '8':
                    searchByName();
                    break;
                case '9':
                    booEx = true;
                    break;
                default:
                    System.out.println("Invalid menu entry. Please try again.");

            }
        }
    }

    private void showPens() {
        List<Pen> penList = penService.getPenDAO().findAll();
        System.out.println();
        for (int i = 0; i < penList.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + penList.get(i));
        }
    }

    private void showHistoryByCustomer() {
        int input = 0;
        boolean booEx = false;

        List<User> userList = userService.getUserDAO().findAll();

        System.out.println();

        for (int i = 0; i < userList.size(); i++){
            System.out.println("[" + (i + 1) + "]" + userList.get(i));
        }

        try {
            while (!booEx) {
                System.out.print("\nPlease choose which customer's order history you'd like to view by referencing [user id]: ");
                input = scanner.nextInt();
                System.out.println();

                List<History> historyList = historyService.getHistoryDAO().findHistoryById(input);

                if (input <= historyList.size()) {
                    for (History h : historyList) {
                        System.out.println(h.getDate() + " | " + penService.getPenDAO().findPenById(h.getFp_id()));
                        booEx = true;
                    }
                } else {
                    System.out.println("Invalid input or the user has no order history.");
                    break;
                }


            }
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please try again.");
            start();
        }
    }

    private void showHistoryByLocation() {

        int input = 0;
        boolean booEx = false;

        List<Location> locList = locService.getLocDAO().findAll();
        System.out.println();
        for (int i = 0; i < locList.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + locList.get(i));
        }

        while (!booEx) {


            System.out.print("\nPlease choose which location's order history you'd like to view by referencing [location id]: ");
            input = scanner.nextInt();
            System.out.println();

            List<History> historyList = historyService.getHistoryDAO().findHistoryByLocId(input);

            if (input <= historyList.size()) {
                for (History h : historyList) {

                    System.out.println(h.getDate() + "\n\n" + penService.getPenDAO().findPenById(h.getFp_id()) + "\n" + userService.getUserDAO().findById(h.getUser_id()) + "\n------------\n");

                    booEx = true;
                }
            } else {
                System.out.println("Invalid input or this location has made no sales. Please try again.");
                booEx = true;
            }
        }
    }

    private void viewLocationInventory() {

        int input = 0;
        boolean booEx = false;

        List<Location> locList = locService.getLocDAO().findAll();
        System.out.println();

        for (int i = 0; i < locList.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + locList.get(i));
        }

        while (!booEx) {
            System.out.print("\nPlease choose which location's inventory you'd like to view by referencing [location id]: ");
            input = scanner.nextInt();

            List<Pen> penList = penService.getPenDAO().findPenByLocation(input);

            for (int i = 0; i < penList.size(); i++){
                System.out.println(penList.get(i));
            }

            break;
        }

    }

    private void invAdjust() {
        int input = 0;
        int qty = 0;
        int fp_id = 0;
        boolean booEx = false;
        List<Pen> penList = penService.getPenDAO().findAll();
        System.out.println();
        for (int i = 0; i < penList.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + penList.get(i));
        }

        while(!booEx) {
            System.out.print("Please choose which pen's inventory you'd like to adjust by referencing the [number listed]: ");

            input = scanner.nextInt() - 1;

            if (input < penList.size()) {
                fp_id = (input + 1);

                Pen selectedPen = penList.get(input);
                System.out.println("You've chosen " + selectedPen.getBrand() + " " + selectedPen.getModel() + ". We currently have " + selectedPen.getQty() + " in stock.");
                System.out.print("\nWould you like to change the inventory quantity for the " + selectedPen.getModel() + "? (y/n) ");

                if (scanner.next().charAt(0) == 'y') {
                    System.out.print("Please enter the new quantity number for the " + selectedPen.getModel() + ": ");
                    qty = scanner.nextInt();
                    selectedPen.setQty(qty);
                    System.out.print("\nYou've set the " + selectedPen.getModel() + "'s inventory quantity to " + qty + ". Is this correct? (y/n) ");

                    if (scanner.next().charAt(0) == 'y') {
                        penService.getPenDAO().adjustPenQuantity(fp_id, qty);

                        break;
                    }
                }
            }
        }
    }

    private void addNewPen() {

        Pen pen = new Pen();

        boolean booEx = false;

        String brand = "";
        String model = "";
        float price = 0.00f;
        int qty = 0;
        String description = "";
        String nib = "";
        int loc_id = 0;

        System.out.println("--- Enter a new pen ---");

        try {

            while (!booEx) {
                System.out.print("Pen brand: ");
                brand = scanner.next();
                pen.setBrand(brand);
                System.out.print("Pen model: ");
                model = scanner.next();
                pen.setModel(model);
                System.out.print("Pen price: ");
                price = scanner.nextFloat();
                pen.setPrice(price);
                System.out.print("What is the initial quantity?: ");
                qty = scanner.nextInt();
                pen.setQty(qty);
                System.out.print("Please provide a description: ");
                description = scanner.next();
                pen.setDescription(description);
                System.out.print("Nib type: ");
                nib = scanner.next();
                pen.setNib(nib);
                System.out.println("At which location will this pen be located?");
                System.out.print("[1] Jackson, MS [2] Cincinnati, OH [3] Newport, KY: ");
                loc_id = scanner.nextInt();
                pen.setLoc_id(loc_id);

                System.out.println("\nPlease confirm the pen's details.");
                System.out.println("\nBrand: " + brand);
                System.out.println("Model: " + model);
                System.out.println("Price: $" + price);
                System.out.println("Initial quantity: " + qty);
                System.out.println("Description: " + description);
                System.out.println("Nib type: " + nib);
                System.out.print("Location: ");
                if (loc_id == 1)  {
                    System.out.println("Jackson, MS");
                } else if (loc_id == 2) {
                    System.out.println("Cincinnati, OH");
                } else if (loc_id == 3) {
                    System.out.println("Newport, KY");
                }
                System.out.print("\nIs this correct? (y/n) ");

                if (scanner.next().charAt(0) == 'y') {
                    penService.getPenDAO().save(pen);
                    System.out.println(pen.getBrand() + " " + pen.getModel() + " has been successfully added to location " + pen.getLoc_id() + "'s inventory.");
                    booEx = true;
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            booEx = true;
        }
    }

    private void sortByDate() {

        List<History> historyList = historyService.getHistoryDAO().sortByDate();

        boolean booEx = false;
        while(!booEx) {

            for (History h : historyList) {
                System.out.println(".___________________________________________________________________________________________________________.");
                System.out.println("| Order Date: " + h.getDate() + " History ID: " + h.getHist_id() + " | Pen ID: " + h.getFp_id() + " | Location ID: " + h.getLoc_id() +
                        " | Price: " + h.getPrice() + " | User ID: " + h.getUser_id() + " |");
                System.out.println("'-----------------------------------------------------------------------------------------------------------'");
            }

//            for (int i = 0; i < historyList.size(); i++) {
//                System.out.println(historyList.get(i));
//            }

            booEx = true;
        }

    }

    private void searchByName() {

        String firstName = "";
        String lastName = "";

        boolean booEx = false;

        while(!booEx) {
            System.out.print("Please enter the user's last name: ");
            lastName = scanner.next().substring(0, 1).toUpperCase(Locale.ROOT);
            System.out.print("\nPlease enter the user's first name: ");
            firstName = scanner.next().substring(0, 1).toUpperCase(Locale.ROOT);
            System.out.println();

            List<User> userList = userService.getUserDAO().findByName(firstName, lastName);

            if (userList.isEmpty()) {
                System.out.println("There are no accounts with that name. Please try again.");
                break;
            }


            for (User u : userList) {
                System.out.println("User ID: " + u.getUser_id() + ", Name: " + u.getFirstName() + " " + u.getLastName() + ", Username: " + u.getUsername() + "\nAddress: "
                + u.getAddress() + " " + u.getCity() + ", " + u.getState() + " " + u.getZip() + "\n");

            }

            booEx = true;
        }

    }
}
