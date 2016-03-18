/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.ShoppingCart;

/**
 *
 * @author x00123757
 */

public class ShopCtrl {
    
   
    private static ArrayList<models.Scart_Prod> shopCart = new ArrayList<>();
    public ShopCtrl(){
        
    }
    
    public void addProductCart(models.Product product,int qty,ShoppingCart cart)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //add product method goes here . 
        models.Scart_Prod cart1 = new models.Scart_Prod(qty, product,cart );
       shopCart.add(cart1);
        em.persist(cart1);
        em.getTransaction().commit();
        emf.close();
    }
    
    public void viewCart(ShoppingCart cart)
    {
        for(int i = 0 ; i < shopCart.size();i++)
        {
            if(cart.getUser().getUserId() == shopCart.get(i).getCart().getUser().getUserId())
            {
                System.out.println(shopCart.get(i));
                System.out.println("Total price"+cart.getTotalPrice());
            }
            
        }
    }
    
}
