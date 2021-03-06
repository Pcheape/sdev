/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;

/**
 *
 * @author Phillip cheape x00123757
 */
@Entity
@Table(name = "CART")
public class ShoppingCart {

    @Id
    private int cartID;

    @OneToMany(cascade = ALL, mappedBy = "cart")
    private static List<Scart_Prod> scart_cartList;



    public ShoppingCart() {

    }

    public ShoppingCart(Users u1) {
        this.cartID = u1.getUserId();
      

    }

    public int getCartID() {
        return cartID;
    }

    public static List<Scart_Prod> getScart_cartList() {
        return scart_cartList;
    }

    public void removeCart() {
        
        for (int i = 0; i < scart_cartList.size(); i++) {
            
            if (this.getCartID() == scart_cartList.get(i).getCartID()) {
                scart_cartList.remove(i);
            }
        }
    }

}
