package controller;
import models.Product;
import models.Supplier;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


public class SupplierCtrl {

   static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
   static EntityManager em = emf.createEntityManager();
    
   static List<Supplier> supplierList = new ArrayList<>();

    public static Supplier createSupplier(int sup_id, String company, String address, String phone) {
        em.getTransaction().begin();
        Supplier supp = new Supplier(sup_id, company, address, phone);
        supplierList.add(supp);
        em.persist(supp);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return supp;
        
    }

    public static List<Supplier> getSupplierList() {
        return supplierList;
    }
    
    
    
}
