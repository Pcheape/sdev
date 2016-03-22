/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.UserCtrl;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import models.Product;
import models.Scart_Prod;
import models.ShoppingCart;

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

            try {
                controller.UserCtrl.firstRun();
            } catch (models.UserNameExists e) {

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
                        case 1:
                            controller.ShopCtrl.printContents(u1.getUserId());
                            break;
                        case 2:
                            shopping(u1);
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
                            manageProducts();
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

    public static void shopping(models.Users u1) {
        List<Product> products = new ArrayList();
        List<ShoppingCart> cart = new ArrayList();
        ShoppingCart userCart = null;
        Product productToBuy = null;
        int productID;
        boolean productFound = false;
        int quantity;
        
        try {
            System.out.println("Press 1 to list all products");
            System.out.println("Press 2 to add product to cart");
            System.out.println("press 3 to logout");
            choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    controller.ProductCtrl.listAllShelfProduct();
                    break;
                case 2:
                    controller.ProductCtrl.listAllShelfProduct();
                    System.out.println("Please enter product ID you would like to purchase");
                    productID = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter quantity");
                    quantity = in.nextInt();
                    
                    
                    products = controller.ProductCtrl.findAllProducts();
                    cart = controller.ShopCtrl.findAllCarts();
                    
                    for(int i = 0; i <cart.size();i++)
                    {
                        if( u1.getUserId() == cart.get(i).getCartID())
                        {
                         userCart = cart.get(i);   
                            System.out.println("cart found");
                        }
                      
                    }
                for(int i = 0 ; i < products.size();i++ )
                {
                    if(productID == products.get(i).getPr_id())
                    {
                        productToBuy = products.get(i);
                        productFound = true;
                        System.out.println("prod found");
                    }
                }
                if(productFound == false)
                {
                    System.out.println("Sorry Product "+productID+" not Available please try again");   
                }
                else{
                    System.out.println("adding prod to cart");
                    controller.ShopCtrl.addProductCart(productToBuy, quantity, userCart);
                    System.out.println("Product added Please purchase at purchase menu thank you for your custom");
                }
                    
                    
                    
                case 3:
                    logout();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid option please try again");
            choice = 0;
            in.nextLine();
        }
    }

    public static void manageProducts() {
       int pr_id;
       String descr;
       double price;
       int qtyOnShelf;
        try {
            System.out.println("press 1 to list all products");
            System.out.println("Press 2 to add a product");
            System.out.println("press 3 to delete a product");
            System.out.println("press 4 to update shelf quantity");
            System.out.println("press 5 to update product description");
            System.out.println("press 6 to logout");

            choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    controller.ProductCtrl.listAllShelfProduct();
                    break;
                case 2:
                    System.out.println("Please enter prodID");
                    pr_id = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter product description");
                    descr = in.nextLine();
                    System.out.println("Please enter product price");
                    price = in.nextDouble();
                    in.nextLine();
                    System.out.println("Please enter quantity in stock");
                    qtyOnShelf = in.nextInt();
                    controller.ProductCtrl.createProduct(pr_id, descr, price, qtyOnShelf);
                    System.out.println("Product added Thank you");
                    break;
                case 3:
                    //del product goes here 
                    break;
                case 4:
                    controller.ProductCtrl.listAllShelfProduct();
                    System.out.println("Please enter prodID");
                    pr_id = in.nextInt();
                    in.nextLine();
                     System.out.println("Please enter quantity in stock");
                    qtyOnShelf = in.nextInt();
                    
                    controller.ProductCtrl.updateShelfQty(pr_id, qtyOnShelf);
                    System.out.println("product : "+pr_id+" with quantity :"+qtyOnShelf+" thank you");
                    break;
                case 5: 
                    controller.ProductCtrl.listAllShelfProduct();
                    System.out.println("Please enter prodID");
                    pr_id = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter product description");
                    descr = in.nextLine();
                    controller.ProductCtrl.updateProdDescr(pr_id, descr);
                    System.out.println("product : "+pr_id+" with description :"+descr+" thank you");
                    break;
                case 6:
                    logout();
                    
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid option please try again");
            choice = 0;
            in.nextLine();
        }

    }

    public static void logout() {
        System.out.println("thank you for using shop r us");
        System.exit(0);
    }

}
