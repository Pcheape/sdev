package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Jurijs
 */
@Entity
@Table(name = "Supplier")
public class Supplier implements Serializable {

    @Id
    @Column(name = "SUP_ID")
    private int sup_id;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PROD_SUPP",
            joinColumns = @JoinColumn(name = "SUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "PR_ID"))
    private List<Product> prodList = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(int sup_id, String company, String address, String phone) {
        this.sup_id = sup_id;
        this.company = company;
        this.address = address;
        this.phone = phone;
    }

    public int getSup_id() {
        return sup_id;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Product> getProdList() {
        return prodList;
    }

    public void setProdList(List<Product> prodList) {
        this.prodList = prodList;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supid=" + sup_id + ", company=" + company + ", address=" + address + ", phone=" + phone + ", prodList=" + prodList + '}';
    }
    
    
}
