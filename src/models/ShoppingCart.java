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
 * @author cytex
 */
@Entity
@Table(name = "CART")
public class ShoppingCart {
    

    
@Id
@OneToOne(cascade = CascadeType.ALL, optional = false,  orphanRemoval = true)
@PrimaryKeyJoinColumn
private models.Users user;

    @OneToMany(cascade = ALL, mappedBy="CartID")
    private List<models.Scart_Prod> cart;
    private double totalPrice;



    public ShoppingCart() {
        
    }
    
    public ShoppingCart(Users u1)
    {
        this.user = u1;
        this.totalPrice= 0;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    

 

    public Users getUser() {
        return user;
    }

    

   

    
}
