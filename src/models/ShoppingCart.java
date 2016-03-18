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
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartseq")
    @SequenceGenerator(name = "cartseq", sequenceName = "cartseq", allocationSize = 1)
private int cartId;
@OneToOne
@JoinColumn(name ="UserID")
private int userID;



    public ShoppingCart(int userID) {
        this.userID = userID;
    }

   

    
}
