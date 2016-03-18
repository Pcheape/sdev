/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author cytex
 */
@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userseq")
    @SequenceGenerator(name = "userseq", sequenceName = "userseq", allocationSize = 1)
    private int UserId;
    
    private String userName;
    private String password;
//   private String type;

//    List<Users> users = new ArrayList<>();
    public Users() {

    }

    public Users(String userName, String password) {

        this.userName = userName;
        this.password = password;
//        this.type = type;

    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        String output;
        output = String.format("Users{" + "UserId=" + UserId + ", userName=" + userName + ", password=" + password + '}'+"\n");
        return output;
    }

}


