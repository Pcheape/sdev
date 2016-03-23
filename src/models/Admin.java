/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Phillip Cheape x00123757
 */
 @Entity
@Table(name = "USERS")
@DiscriminatorValue(value = "Admin")
public class Admin extends Users{
     
     public Admin()
     {
         
     }
     
     public Admin(String userName, String password)
     {
         super(userName,password);
     }
   
    
}
