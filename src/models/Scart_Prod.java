package models;

/**
 *
 * @author Jurijs
 */
import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;
import models.ShoppingCart;


@Entity(name = "Scart_Prod")
public class Scart_Prod implements Serializable {

   @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartseq")
    @SequenceGenerator(name = "cartseq", sequenceName = "cartseq", allocationSize = 1)
    @Column(name = "SPR_ID")
    private int sPr_id;

    @Column(name = "PR_QTY")
    private int pr_qty;

    //change OneToOne
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PR_ID")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "cartID")
    private models.ShoppingCart cart;
    
   @Transient
    int cartID;

    public Scart_Prod() {

    }

    public Scart_Prod(int pr_qty, Product product, ShoppingCart cart) {
        this.pr_qty = pr_qty;
        this.product = product;
       this.cart = cart;
       this.cartID = cart.getCartID();
    }

    public int getsPr_id() {
        return sPr_id;
    }

    public void setsPr_id(int sPr_id) {
        this.sPr_id = sPr_id;
    }

    public int getPr_qty() {
        return pr_qty;
    }

    public void setPr_qty(int pr_qty) {
        this.pr_qty = pr_qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public int getCartID() {
        return cartID;
    }

    
    
    @Override
    public String toString() {
        String output;
        
       output = "Product : "+product.getDescr()+"Quantity: "+pr_qty+" Price "+(product.getPrice()*pr_qty);
      
       
       return output;
    }
   
    
    
}
