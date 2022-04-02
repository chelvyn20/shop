package id.co.nds.shop.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import id.co.nds.shop.keys.SaleProductKey;

@Entity
@Table(name = "sale_product")
public class SaleProductEntity {
    @EmbeddedId
    private SaleProductKey id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @MapsId("saleId")
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private SaleEntity sale;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    public SaleProductEntity() {}

    public SaleProductEntity(SaleEntity sale, ProductEntity product, CategoryEntity category,
            BigDecimal price, Integer quantity) {
        this.id = new SaleProductKey(sale.getId(), product.getId(), category.getId());
        this.sale = sale;
        this.product = product;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public SaleProductKey getId() {
        return id;
    }

    public void setId(SaleProductKey id) {
        this.id = id;
    }

    public SaleEntity getSale() {
        return sale;
    }

    public void setSale(SaleEntity sale) {
        this.sale = sale;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
