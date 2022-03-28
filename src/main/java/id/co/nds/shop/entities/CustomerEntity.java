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
@Table(name = "ms_customer")
public class CustomerEntity {
    @Id
    @GenericGenerator(name = "customer_id_seq", strategy = "id.co.nds.shop.generators.CustomerIdGenerator")
    @GeneratedValue(generator = "customer_id_seq")
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "call_number")
    private String callNumber;

    @JsonBackReference
    @OneToMany(targetEntity = SaleEntity.class, mappedBy = "customer")
    private List<SaleEntity> sales;

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

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public List<SaleEntity> getSales() {
        return sales;
    }

    public void setSales(List<SaleEntity> sales) {
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
