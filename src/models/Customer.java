/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import javax.persistence.*;


/**
 *
 * @author cytex
 */
@Entity
@Table(name = "USERS")
@DiscriminatorValue(value = "Customer")
public class Customer extends Users  {
    
    private String name;
    private String address;
    
    public Customer()
    {
        
    }

    public Customer(String userName, String password,String name, String address) {
        super(userName,password);
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        String output;
        output = super.toString();
        output +=  "name=" + name + ", address=" + address ;
        return output;
    }
    
    
    
}
