package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Column(name = "DESCR")
    private String descr;

    @Column(name = "PRICE")
    private double price;

    //change MAP
    @MapKeyColumn(name = "SHELFNUM")
    private Map<String, Product> shelfMap;

    @Column(name = "QTYONSHELF")
    private int qtyOnShelf;

    //product-supplier
    @ManyToMany(mappedBy = "prodList")
    private List<Supplier> supList = new ArrayList<>();

    //changed OneToOne ,product - scart_prod    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product")
    private Scart_Prod owner;

    public Product() {
        owner = new Scart_Prod();
        shelfMap = new HashMap<>();//*
    }

    public Product(int pr_id, String descr, double price, int qtyOnShelf) {

        owner = new Scart_Prod();//product - scart_prod
        shelfMap = new HashMap<>();//*

        this.pr_id = pr_id;
        this.descr = descr;
        this.price = price;
        this.qtyOnShelf = qtyOnShelf;
    }
    
    //**

    public void putProductOnShelf(String shelfnum, Product p) {
        shelfMap.put(shelfnum, p);
    }

    public void listProductShelf() {
        for (Map.Entry<String, Product> entry : shelfMap.entrySet()) {
            System.out.printf("Shelf : %s Product: %s %n",
                    entry.getKey(), entry.getValue().getDescr());
        }
    }

    public void addSupplier(Supplier s) {
        supList.add(s);
        s.getProdList().add(this);
    }

    public void removeSupplier(Supplier s) {
        supList.remove(s);//number is changed from 2 to 1
        s.getProdList().remove(this);//this refers to current instance of product(p3.remove - call remove on product)
    }

    public void removeProductSupplier() {
        ArrayList<Supplier> temp = new ArrayList<>(supList);//temporary array supList
        for (int i = 0; i < temp.size(); i++) {//it changes to 1, but temp stays 2
            removeSupplier(temp.get(i));
        }
    }
    //***

    public int getQtyOnShelf() {
        return qtyOnShelf;
    }

    public void setQtyOnShelf(int qtyOnShelf) {
        this.qtyOnShelf = qtyOnShelf;
    }

    public Scart_Prod getOwner() {
        return owner;
    }

    public void setOwner(Scart_Prod owner) {
        this.owner = owner;
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

    public List<Supplier> getSupList() {
        return supList;
    }

    public void setSupList(List<Supplier> supList) {
        this.supList = supList;
    }

    @Override
    public String toString() {
        return "Product{" + "prodid=" + pr_id + ", descr=" + descr + ", price=" + price + ", supList=" + supList + '}';
    }
    
    
    
    

}
