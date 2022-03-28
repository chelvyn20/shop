package id.co.nds.shop.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ms_category")
public class CategoryEntity {
    @Id
    @GenericGenerator(name = "category_id_seq", strategy = "id.co.nds.shop.generators.CategoryIdGenerator")
    @GeneratedValue(generator = "category_id_seq")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @OneToMany(targetEntity = ProductEntity.class, mappedBy = "category")
    private List<ProductEntity> products;

    @JsonBackReference
    @OneToMany(targetEntity = SaleProductEntity.class, mappedBy = "category")
    private List<SaleProductEntity> saleTrx;

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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
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