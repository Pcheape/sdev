/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author cytex
 */
public class UserNameExists extends Exception{
    
    public UserNameExists()
    {
        super("sorry user name is taken please insert new username");
    }
    
}
