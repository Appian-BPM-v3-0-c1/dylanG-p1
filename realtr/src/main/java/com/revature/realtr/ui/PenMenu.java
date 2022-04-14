package com.revature.realtr.ui;

import com.revature.realtr.models.Cart;
import com.revature.realtr.models.History;
import com.revature.realtr.models.Pen;
import com.revature.realtr.models.User;
import com.revature.realtr.services.CartService;
import com.revature.realtr.services.HistoryService;
import com.revature.realtr.services.PenService;
import com.revature.realtr.util.CartIsEmptyException;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class PenMenu implements iMenu {

    private final User user;
    private final PenService penService;
    private final CartService cartService;
    private final HistoryService historyService;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public PenMenu(User user, PenService penService, CartService cartService, HistoryService historyService) {
        this.user = user;
        this.penService = penService;
        this.cartService = cartService;
        this.historyService = historyService;

    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        char userInput = ' ';
        boolean booEx = false;

        System.out.println("\nWelcome, " + user.getFirstName());

        exit: {
            while (!booEx) {
                System.out.println("\nPlease choose from the following options:");
                System.out.println("[1] View our collection");
                System.out.println("[2] View order history");
                System.out.println("[3] View cart");
                System.out.println("[4] Sign out\n");

                System.out.print("Selection: ");

                userInput = scanner.next().charAt(0);

                switch (userInput) {
                    case '1':
                        showPens();
                        break;
                    case '2':
                        viewHistory();
                        break;
                    case '3':
                        viewCart();
                        break;
                    case '4':
                        break exit;
                    default:
                        System.out.println("Invalid menu entry. Please try again.");
                        break;

                }
            }
        }
    }

    private void showPens() {

        List<Pen> penList = penService.getPenDAO().findAll();

        for (int i = 0; i < penList.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + penList.get(i));

        }

        System.out.print("Would you like to buy a pen? (y/n) ");

        if (scanner.next().charAt(0) == 'y') {
            buyPens(penList);
        } else {
            System.out.println("Invalid input. Please try again.\n");
            System.out.print("Selection: ");
        }
    }

    private void buyPens(List<Pen> penList) {
        int input = 0;
        boolean booEx = false;

        while (!booEx) {
            System.out.print("\nPlease choose which pen you'd like to purchase referencing the [number listed]: ");

            input = scanner.nextInt() - 1;

            if (input < penList.size()) {
                Pen selectedPen = penList.get(input);

                System.out.println("You've chosen the " + selectedPen.getBrand() + " " + selectedPen.getModel() + ".");
                System.out.print("Would you like to add this to your cart? (y/n) ");

                if (scanner.next().charAt(0) == 'y') {

                    if (selectedPen.getQty() > 0) {
                        Cart cart = new Cart();
                        cart.setFp_id(selectedPen.getFp_id());
                        cart.setLoc_id(selectedPen.getLoc_id());
                        cart.setUser_id(user.getUser_id());
                        cart.setPrice(selectedPen.getPrice());
                        cartService.getCartDAO().save(cart);

                        System.out.println("\n" + selectedPen.getBrand() + " " + selectedPen.getModel() + " has been added to your cart");

                        break;

                    } else if (selectedPen.getQty() == 0) {
                        System.out.println("Pen not in stock. Please choose another pen.");
                        break;
                    }

                } else if (scanner.next().charAt(0) == 'n') {
                    break;

                } else {
                    System.out.println("\nInvalid input. Please try again.");
                    break;
                }

            } else {
                System.out.println("\nInvalid input. Please try again.");
                break;
            }
        }
    }

    private void viewCart() {



        List<Cart> carts = cartService.getCartDAO().findCartById(user.getUser_id());

        try {
            if (carts.isEmpty()) {
                throw new CartIsEmptyException();
            }
        } catch (CartIsEmptyException e) {
            System.out.println("This is a custom exception informing the user that their cart is empty. Please try again.");
            start();

        }

        for (Cart c : carts) {
            System.out.println(penService.getPenDAO().findPenById(c.getFp_id()));
        }

        System.out.println("Your total is: $" + df.format(cartService.getCartDAO().totalPrice(user.getUser_id())));
        System.out.print("\nWould you like to checkout? (y/n) ");


        if (scanner.next().charAt(0) == 'y') {

            checkout(carts);

        } else {

            System.out.print("Would you like to clear your cart? (y/n) ");

            if (scanner.next().charAt(0) == 'y') {
                cartService.getCartDAO().removeById(user.getUser_id());
            } else {
                start();
            }

        }

    }



    private void checkout(List<Cart> carts) {

        History history = new History();

        for (Cart c : carts) {
            history.setFp_id(c.getFp_id());
            history.setUser_id(c.getUser_id());
            history.setLoc_id(c.getLoc_id());

            historyService.getHistoryDAO().save(history);
            penService.getPenDAO().removeById(c.getFp_id());
        }

        System.out.println("\nCheckout completed. Thank you for shopping at plume, " + user.getFirstName() + ".");

        for (Cart c : carts) {
            cartService.getCartDAO().removeById(c.getUser_id());
        }
    }

    private void viewHistory() {

        List<History> historyList = historyService.getHistoryDAO().findHistoryById(user.getUser_id());

        for (History h : historyList) {
            System.out.println(h.getDate() + " | " + penService.getPenDAO().findPenById(h.getFp_id()));
        }

    }

}
