/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.UserCtrl;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author cytex
 */
public class Menu {

    private static Scanner in = new Scanner(System.in);
    private static int choice;
    private static boolean firstRun = true;

    public static void welcome() throws models.UserNameExists {

        if (firstRun) {
            System.out.println("adding users");
            try{
            controller.UserCtrl.firstRun();
            }catch(models.UserNameExists e){
                
            }
            firstRun = false;
        }

        do {
            try {
                System.out.println("Welcome to Shop R US");
                System.out.println("Please press 1 to login");
                System.out.println("Please press 2 to register");
                System.out.println("Please press 3 to exit");

                choice = in.nextInt();
                in.nextLine();

                switch (choice) {
                    case 1:
                        login((ArrayList) controller.UserCtrl.getUsers());
                        break;

                    case 2:
                        register("customer");
                        break;
                    case 3:
                        logout();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option please try again");
                choice = 0;
                in.nextLine();
            }
        } while (choice != 3);

    }

    public static void register(String type) {

        String userName;
        String password;
        String name;
        String address;

        System.out.println("Please enter your username");
        userName = in.nextLine();

        System.out.println("Please enter your Password");
        password = in.nextLine();
        if (type.equals("admin")) {
            try {
                UserCtrl.addAdmin(userName, password);
            } catch (models.UserNameExists e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Please enter your name");
            name = in.nextLine();
            System.out.println("Please enter your billing address");
            address = in.nextLine();
            try {
                UserCtrl.addUser(userName, password, name, address);
            } catch (models.UserNameExists e) {
                System.out.println(e.getMessage());
            }

        }
    }

    

    public static void login(ArrayList<models.Users> u1) {

        String username;
        String password;
        boolean loggedIn = false;

        System.out.println("Please enter username");
        username = in.nextLine();
        System.out.println("Please enter Password");
        password = in.nextLine();
        System.out.println("size:" + u1.size());
        for (int i = 0; i < u1.size(); i++) {

            if ((u1.get(i).getUserName().equals(username)) && (u1.get(i).getPassword().equals(password))) {
                System.out.println("Logging in ");

                orderMenu(u1.get(i));
                loggedIn = true;
            }

        }
        if (!loggedIn) {
            System.out.println("Password incorrect");
        }

    }

    public static void orderMenu(models.Users u1) {

        if (u1 instanceof models.Customer) {
            do {
                try {
                    System.out.println("Welcome to the order menu");
                    System.out.println("Press 1 to view shopping cart ");
                    System.out.println("Press 2 to Shopping menu ");
                    System.out.println("Press 3 to logout");
                    choice = in.nextInt();
                    in.nextLine();

                    switch (choice) {
                        case 3:
                            logout();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid option please try again");
                    choice = 0;
                    in.nextLine();
                }
            } while (choice != 3);

        } else if (u1 instanceof models.Admin) {
            do {
                try {
                    System.out.println("Press 1 to view all User accounts");
                    System.out.println("Press 2 to add a admin");
                    System.out.println("press 3 to manage products");
                    System.out.println("Press 4 to logout");
                    choice = in.nextInt();
                    in.nextLine();

                    switch (choice) {
                        case 1:
                            controller.UserCtrl.printUsers();
                            break;
                        case 2:
                            register("admin");
                            break;
                        case 3:
                            break;
                        case 4:
                            logout();
                            break;

                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid option please try again");
                    choice = 0;
                    in.nextLine();
                }
            } while (choice != 4);

        }
    }

    public static void logout() {
        System.out.println("thank you for using shop r us");
        System.exit(0);
    }

}
