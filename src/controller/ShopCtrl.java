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
        System.out.println("commiting");
        em.close();
        emf.close();
    }
    
      public static List<ShoppingCart> findAllCarts() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM ShoppingCart e");
        
        return (List<ShoppingCart>) query.getResultList();
    }
      
   
      public static void updateTotalPrice(ShoppingCart cart, double price)
      {
          cart.setTotalPrice(price);
      }
      
      
    
    public static void printContents(int userId)
    {
        List<Scart_Prod> cartList;
        cartList = models.ShoppingCart.getScart_cartList();
        System.out.println("size"+cartList.size());
//        for(int i = 0 ; i < cartList.size();i++)
//        {
//            if(cartList.get(i).getCartID() == userId)
//            {
//            System.out.println(cartList.get(i));
//            }
//        }
    }
    
    
    public static void removeCart(ShoppingCart cart)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        cart.removeCart(cart);
        em.merge(cart);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static ArrayList<Scart_Prod> getShopCart() {
        return shopCart;
    }
    

    
}
