package id.co.nds.shop.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tx_sale")
public class SaleEntity {

    @Id
    @GenericGenerator(name = "sale_id_seq", strategy = "id.co.nds.shop.generators.SaleIdGenerator")
    @GeneratedValue(generator = "sale_id_seq")
    @Column(name = "id")
    private String id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @JsonIgnoreProperties("hibernateLazyInitializer")
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    @JsonManagedReference
    @OneToMany(targetEntity = SaleProductEntity.class, mappedBy = "sale")
    private List<SaleProductEntity> saleProducts;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "created_by")
    private Integer createdBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<SaleProductEntity> getSaleProducts() {
        return saleProducts;
    }

    public void setSaleProducts(List<SaleProductEntity> saleProducts) {
        this.saleProducts = saleProducts;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

}
