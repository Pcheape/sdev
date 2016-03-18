/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import javax.persistence.*;

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
private Users user;



    public ShoppingCart() {
        
    }
    
    public ShoppingCart(Users u1)
    {
        this.user = u1;
    }

   

    
}
