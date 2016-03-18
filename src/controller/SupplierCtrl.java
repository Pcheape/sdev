package controller;
import models.Product;
import models.Supplier;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


public class SupplierCtrl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShopPU");
    EntityManager em = emf.createEntityManager();
    
    
//    public Supplier createSupplier(int sup_id, String company, String address, String phone) {
//        em.getTransaction().begin();
//        Supplier supp = new Supplier(sup_id, company, address, phone);        
//        productList.add(supp);
//        em.persist(supp);
//        em.getTransaction().commit();
//        return supp;
//    }
//    
//    //services
//    public void addSupplier(Supplier s) {
//        supList.add(s);
//        s.getProdList().add(this);
//    }
    

    
}
