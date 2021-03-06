package controller;

import models.Product;
import models.Scart_Prod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
import models.Supplier;

public class ProductCtrl {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
    static EntityManager em = emf.createEntityManager();

    private static List<Product> productList = new ArrayList<>();

    public static void firstRun() {
        Product p1;
        Collection<Product> existingProducts;
        existingProducts = findAllProducts();

        Iterator itr = existingProducts.iterator();
        while (itr.hasNext()) {

            p1 = (Product) itr.next();

            productList.add(p1);
        }
    }

    public static Product createProduct(int pr_id, String descr, double price, int qtyOnShelf, Supplier sup) {
        em.getTransaction().begin();
        Product p = new Product(pr_id, descr, price, qtyOnShelf, sup);
        productList.add(p);
        em.persist(p);
        em.getTransaction().commit();

        return p;
    }

    public static List<Product> findAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return (List<Product>) query.getResultList();
    }

    public static void updateProdDescr(int pr_id, String newDescr) {
        em.getTransaction().begin();
        Product p = em.find(Product.class, pr_id);
        p.setDescr(newDescr);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    public static void updateShelfQty(int pr_id, int qtyOnShelf) {
        em.getTransaction().begin();
        Product p = em.find(Product.class, pr_id);

        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    public static void PrintProducts() {
        Collection<Product> prodsCol;
        Product products;
        List<Product> prodList = new ArrayList<>();

        prodsCol = findAllProducts();

        Iterator itr = prodsCol.iterator();
        while (itr.hasNext()) {

            products = (Product) itr.next();

            prodList.add(products);

            for (int i = 0; i < prodList.size(); i++) {
                System.out.println(prodList.get(i));
            }

        }
    }
    
    public static void deleteProduct(int prodId)
    {
        
          Collection<Product> prodsCol;
        Product products = null;
        List<Product> prodList = new ArrayList<>();
        Product prodToDelete;

        prodsCol = findAllProducts();

        Iterator itr = prodsCol.iterator();
        while (itr.hasNext()) {

            products = (Product) itr.next();

            prodList.add(products);
        }
        
            
            for(int i = 0; i < prodList.size();i++)
            {
                if(prodList.get(i).getPr_id()==prodId)
                {
                    products = prodList.get(i);
                }
            }
        em.getTransaction().begin();
        
        prodToDelete = em.merge(products);
        em.remove(prodToDelete);
        em.getTransaction().commit();
       

    }


    
}
