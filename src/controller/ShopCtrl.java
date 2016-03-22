/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import models.Scart_Prod;

import models.ShoppingCart;

/**
 *
 * @author x00123757
 */

public class ShopCtrl {
    
   
    private static ArrayList<models.Scart_Prod> shopCart = new ArrayList<>();
    public ShopCtrl(){
        
    }
    
    public static void addProductCart(models.Product product,int qty,ShoppingCart cart)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
       
        models.Scart_Prod cart1 = new models.Scart_Prod(qty, product,cart );
       shopCart.add(cart1);
        em.persist(cart1);
        em.getTransaction().commit();
        emf.close();
    }
    
      public static List<ShoppingCart> findAllCarts() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM ShoppingCart e");
        
        return (List<ShoppingCart>) query.getResultList();
    }
    
    public static void printContents(int userId)
    {
        ArrayList<Scart_Prod> cartSearch = new ArrayList<>();
        for(int i = 0; i <cartSearch.size();i++)
        {
            if(cartSearch.get(i).getCart().getCartID()== userId )
            {
            System.out.println(cartSearch.get(i));
            }
        }
    }
    

    
}
