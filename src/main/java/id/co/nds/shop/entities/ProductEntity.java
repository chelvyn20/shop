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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ms_product")
public class ProductEntity {
    @Id
    @GenericGenerator(name = "product_id_seq", strategy = "id.co.nds.shop.generators.ProductIdGenerator")
    @GeneratedValue(generator = "product_id_seq")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    @Size(min = 6, max = 50)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "product_category", joinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id") }, uniqueConstraints = @UniqueConstraint(columnNames = {
                            "product_id", "category_id" }))
    private CategoryEntity category;

    @JsonBackReference
    @OneToMany(targetEntity = SaleProductEntity.class, mappedBy = "product")
    private List<SaleProductEntity> sales;

    // date
    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "deleted_time")
    private Timestamp deletedTime;

    // by
    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "deleted_by")
    private Integer deletedBy;

    // status
    @Column(name = "rec_status")
    private String recStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<SaleProductEntity> getSales() {
        return sales;
    }

    public void setSales(List<SaleProductEntity> sales) {
        this.sales = sales;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Timestamp getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Timestamp deletedTime) {
        this.deletedTime = deletedTime;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

}
