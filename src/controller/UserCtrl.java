/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
//import models.ShoppingCart;
import models.Users;

/**
 *
 * @author cytex
 */
public class UserCtrl {

    private static List<models.Users> users = new ArrayList<>();
    private static List<models.ShoppingCart> scart = new ArrayList<>();

    public static Collection<Users> findAllUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Users e");
        
        return (Collection<Users>) query.getResultList();
    }

    public static void firstRun() throws models.UserNameExists {
       
        Users u1;
        Collection<Users> existingUsers;
        existingUsers = findAllUsers();
       
       
        
        
        Iterator itr = existingUsers.iterator();
        while (itr.hasNext()) {
            
        u1 = (Users) itr.next();
            
        users.add(u1);
        }
        try{ 
       addAdmin("admin","admin");
       }catch(models.UserNameExists e){
           
       }
      
       
    }

    public static void addUser(String userName, String password, String name, String address) throws models.UserNameExists {

         for(int i = 0 ; i <users.size();i++)
        {
            if(users.get(i).getUserName().equals(userName))
            {
                throw new models.UserNameExists();
            }
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        models.Customer c1 = new models.Customer(userName, password, name, address);
        users.add(c1);
        em.persist(c1);
        models.ShoppingCart cart1 = new models.ShoppingCart(c1);
        scart.add(cart1);
        
        
        
        em.persist(cart1);
        em.getTransaction().commit();
        emf.close();

        System.out.println("UserCreated Successfully Please Log in");
        views.Menu.login((ArrayList) users);

    }
    
    public static void addAdmin(String username,String password) throws models.UserNameExists
    {
        for(int i = 0 ; i <users.size();i++)
        {
            if(users.get(i).getUserName().equals(username))
            {
                throw new models.UserNameExists();
            }
        }
        
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
        EntityManager em = emf.createEntityManager();   
         em.getTransaction().begin();
        models.Admin a1 = new models.Admin(username,password);
        users.add(a1);
        em.persist(a1);
        em.getTransaction().commit();
        emf.close();
    }

    public static List<Users> getUsers() {

        return  users;
    }

//    public static List<ShoppingCart> getScart() {
//        return scart;
//    }
    public static void printUsers(){
         Collection<Users> existingUsers;
        existingUsers = findAllUsers();
        System.out.println(existingUsers);
    }

}
