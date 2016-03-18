package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;

/**
 *
 * @author Jurijs
 */


@Entity
@Table(name = "Product")
public class Product implements Serializable {

    @Id
    @Column(name = "PR_ID")
    private int pr_id;

    @Column(name = "descr")
    private String descr;

    @Column(name = "price")
    private double price;

    @Column(name = "shelfnum")
    private String shelfnum;

    //product-supplier
    @ManyToMany(mappedBy = "prodList")
    private List<Supplier> supList = new ArrayList<>();
    
    
    //product - scart_prod
    @OneToMany(cascade = ALL, mappedBy="product")
    private List<Scart_Prod> scart_ProdList;
    
    
    public Product() {
        scart_ProdList =new ArrayList<>();
    }

    public Product(int pr_id, String descr, double price, String shelfnum) {
        
        scart_ProdList =new ArrayList<>();//product - scart_prod
        
        this.pr_id = pr_id;
        this.descr = descr;
        this.price = price;
        this.shelfnum = shelfnum;
    }

    public int getPr_id() {
        return pr_id;
    }

    public void setPr_id(int pr_id) {
        this.pr_id = pr_id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShelfnum() {
        return shelfnum;
    }

    public void setShelfnum(String shelfnum) {
        this.shelfnum = shelfnum;
    }

    public List<Supplier> getSupList() {
        return supList;
    }

    public void setSupList(List<Supplier> supList) {
        this.supList = supList;
    }

    @Override
    public String toString() {
        return "Product{" + "prodid=" + pr_id + ", descr=" + descr + ", price=" + price + ", shelfnum=" + shelfnum + ", supList=" + supList + '}';
    }

    //services
    public void addSupplier(Supplier s) {
        supList.add(s);
        s.getProdList().add(this);
    }
    
    
    
    
    
    

}
