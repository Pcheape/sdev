package controller;
import model.Product;
import model.Scart_Prod;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


public class ProductCtrl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShopPU");
    EntityManager em = emf.createEntityManager();

    List<Product> productList = new ArrayList<>();

    public Product createProduct(int pr_id, String descr, double price, String shelfnum) {
        em.getTransaction().begin();
        Product prod = new Product(pr_id, descr, price, shelfnum);        
        productList.add(prod);
        em.persist(prod);
        em.getTransaction().commit();
        return prod;
    }

    public List<Product> findAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return (List<Product>) query.getResultList();
    }

    public Scart_Prod createScart_Prod(int sPr_id) {
        em.getTransaction().begin();
        Scart_Prod sPr = new Scart_Prod();
        sPr.setsPr_id(sPr_id);         
        em.persist(sPr);
        em.getTransaction().commit();
        return sPr;
    }

    public void updateQty(int id, int newQty) {

        em.getTransaction().begin();
        Scart_Prod sPr = em.find(Scart_Prod.class, id);
        sPr.setPr_qty(newQty);
        em.merge(sPr);
        em.getTransaction().commit();

    }

    public void updateProdDescr(int pr_id, String newName) {
        em.getTransaction().begin();
        Product p = em.find(Product.class, pr_id);
        p.setDescr(newName);
        em.getTransaction().commit();

    }

//    public void removeScart_Prod(int id) {
//        em.getTransaction().begin();
//        Scart_Prod sPr = em.find(Scart_Prod.class, id);
//        sPr.getProduct().re//.getDepartment().removeEmployee(id);
//        em.getTransaction().commit();
//    }

//    public void removeDepartment(int did) {
//        em.getTransaction().begin();
//        Department d = em.find(Department.class, did);
//        Query query = em.createQuery("SELECT sPr FROM Employee sPr where sPr.department.id = " + did);
//        List<Employee> aCollection = (List<Employee>) query.getResultList();
//        for (Employee sPr : aCollection) {
//            d.removeEmployee(sPr);
//        }
//        d.getListEmployees().clear();
//        em.remove(d); // removes department from department table
//        em.getTransaction().commit();
//    }
//
//    public Employee setEmployeeDepartment(int empId, String cubeId, int deptId) {
//        em.getTransaction().begin();
//        Employee sPr = em.find(Employee.class, empId);
//        Department dept = em.find(Department.class, deptId);
//        dept.addEmployee(cubeId, sPr);
//        em.getTransaction().commit();
//        return sPr;
//    }
//
//    public List<Employee> findAllEmployees() {
//        Query query = em.createQuery("SELECT sPr FROM Employee sPr");
//        List<Employee> aCollection = (List<Employee>) query.getResultList();
//        return aCollection;
//    }
//
//    public void findCubicleEmpList() {
//        for (Department dlist1 : productList) {
//            dlist1.getEmployees();
//        }
//    }
}
