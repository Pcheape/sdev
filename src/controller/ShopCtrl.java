/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.ShoppingCart;

/**
 *
 * @author x00123757
 */
public class ShopCtrl {
    
   
    
    public ShopCtrl(){
        
    }
    
    public void addProductCart(models.Product product,int qty,ShoppingCart cart)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //add product method goes here . 
        models.Scart_Prod cart1 = new models.Scart_Prod(qty, product,cart );
        em.persist(cart1);
        em.getTransaction().commit();
        emf.close();
    }
    
}
