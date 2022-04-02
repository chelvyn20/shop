package id.co.nds.shop.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.shop.entities.CategoryEntity;
import id.co.nds.shop.entities.ProductEntity;
import id.co.nds.shop.entities.SaleEntity;
import id.co.nds.shop.entities.SaleProductEntity;
import id.co.nds.shop.generators.DateGenerator;
import id.co.nds.shop.models.ItemModel;
import id.co.nds.shop.models.SaleModel;
import id.co.nds.shop.repos.CustomerRepo;
import id.co.nds.shop.repos.ProductRepo;
import id.co.nds.shop.repos.SaleProductRepo;
import id.co.nds.shop.repos.SaleRepo;

@Service
public class SaleService implements Serializable {
    @Autowired
    public SaleRepo saleRepo;

    @Autowired
    public SaleProductRepo saleProductRepo;

    @Autowired
    public ProductRepo productRepo;

    @Autowired
    public CustomerRepo customerRepo;

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public SaleEntity addSale(SaleModel saleModel) throws Exception {
        SaleEntity sale = new SaleEntity();

        long seq = saleRepo.count() + 1;
        sale.setId("S" + DateGenerator.generateTodayCode() + String.format("%03d", seq));

        BigDecimal totalPrice = new BigDecimal("0");
        sale.setTotalPrice(totalPrice);

        Integer totalQuantity = 0;
        sale.setTotalQuantity(totalQuantity);

        sale.setCustomer(customerRepo.getById(saleModel.getCustomerId()));
        sale.setCreatedTime(DateGenerator.generateTimestamp());
        sale.setCreatedBy(saleModel.getActorId() == null ? 0 : saleModel.getActorId());

        List<SaleProductEntity> saleProductList = new ArrayList<>();

        for (ItemModel item : saleModel.getItems()) {
            ProductEntity product = productRepo.findById(item.getProductId()).orElse(null);
            totalPrice = totalPrice
                    .add(product.getPrice().multiply(new BigDecimal(item.getQuantity())));
            totalQuantity += item.getQuantity();

            product.setStock(product.getStock() - item.getQuantity());
            product.setUpdatedTime(DateGenerator.generateTimestamp());
            product.setUpdatedBy(saleModel.getActorId() == null ? 0 : saleModel.getActorId());
            productRepo.save(product);

            CategoryEntity category = product.getCategory();

            SaleProductEntity saleProduct = new SaleProductEntity(sale, product, category,
                    product.getPrice(), item.getQuantity());
            saleProductList.add(saleProduct);
        }

        saleProductRepo.saveAll(saleProductList);

        sale.setSaleProducts(saleProductList);
        sale.setTotalPrice(totalPrice);
        sale.setTotalQuantity(totalQuantity);
        return saleRepo.save(sale);
    }

}
