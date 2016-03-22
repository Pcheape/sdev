
package controller;
import models.Product;
import models.Scart_Prod;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


public class ProductCtrl {

     static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SdevCAPU");
   static EntityManager em = emf.createEntityManager();

    private static List<Product> productList = new ArrayList<>();

    public static Product createProduct(int pr_id, String descr, double price, int qtyOnShelf) {
        em.getTransaction().begin();
        Product p = new Product(pr_id, descr, price, qtyOnShelf);
        productList.add(p);
        em.persist(p);
        em.getTransaction().commit();
        return p;
    }

    public static void listAllShelfProduct() {
        for (Product p : productList) {
            p.listProductShelf();
        }
    }

    public List<Product> findAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return (List<Product>) query.getResultList();
    }

    public static void updateProdDescr(int pr_id, String newDescr) {
        em.getTransaction().begin();
        Product p = em.find(Product.class, pr_id);
        p.setDescr(newDescr);
        em.getTransaction().commit();

    }

    public void updateShelfQty(int pr_id, int qtyOnShelf) {
        em.getTransaction().begin();
        Product p = em.find(Product.class, pr_id);
        p.setQtyOnShelf(qtyOnShelf);
        em.getTransaction().commit();

    }

    public void deductFromShelf(int pr_id, int qty) {
        em.getTransaction().begin();
        Product p = em.find(Product.class, pr_id);
        if (p.getQtyOnShelf() > qty) {
            updateShelfQty(pr_id, (p.getQtyOnShelf() - qty));
            System.out.println("OK");

        } else {
            System.out.println("Sorry, quantity on shelf is: " + p.getQtyOnShelf());
        }
        em.getTransaction().commit();
    }

    public void addToShelf(int pr_id, int qty) {
        em.getTransaction().begin();
        Product p = em.find(Product.class, pr_id);
        updateShelfQty(pr_id, (p.getQtyOnShelf() + qty));
        em.getTransaction().commit();
    }
}
