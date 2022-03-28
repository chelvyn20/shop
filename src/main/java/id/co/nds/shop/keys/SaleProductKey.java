package id.co.nds.shop.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SaleProductKey implements Serializable {
    @Column(name = "sale_id")
    private String saleId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "category_id")
    private String categoryId;

    public SaleProductKey() {
    }

    public SaleProductKey(String saleId, String productId, String categoryId) {
        this.saleId = saleId;
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((saleId == null) ? 0 : saleId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SaleProductKey other = (SaleProductKey) obj;
        if (categoryId == null) {
            if (other.categoryId != null)
                return false;
        } else if (!categoryId.equals(other.categoryId))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (saleId == null) {
            if (other.saleId != null)
                return false;
        } else if (!saleId.equals(other.saleId))
            return false;
        return true;
    }

}