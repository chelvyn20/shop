package id.co.nds.shop.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public SaleEntity add(SaleModel saleModel) throws Exception {
        // validation

        // process
        SaleEntity initSale = new SaleEntity();

        initSale.setTotalPrice(new BigDecimal("0"));
        initSale.setTotalQuantity(0);
        initSale.setCustomer(customerRepo.getById(saleModel.getCustomerId()));
        initSale.setCreatedTime(DateGenerator.generateTimestamp());
        initSale.setCreatedBy(saleModel.getCreatedBy() == null ? 0 : saleModel.getCreatedBy());

        return saleRepo.save(initSale);
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = { Exception.class })
    public SaleEntity addSaleProductList(SaleEntity sale, SaleModel saleModel) throws Exception {
        // validation

        // process
        List<SaleProductEntity> saleProductList = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal("0");
        Integer totalQuantity = 0;

        for (ItemModel item : saleModel.getItems()) {
            ProductEntity product = productRepo.findById(item.getProductId()).orElse(null);
            CategoryEntity category = product.getCategory();
            totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(item.getQuantity())));
            totalQuantity += item.getQuantity();
            product.setStock(product.getStock() - item.getQuantity());
            productRepo.save(product);

            SaleProductEntity saleProduct = new SaleProductEntity(sale, product, category, product.getPrice(),
                    item.getQuantity());
            saleProductList.add(saleProduct);
        }

        saleProductRepo.saveAll(saleProductList);

        sale.setSaleProducts(saleProductList);
        sale.setTotalPrice(totalPrice);
        sale.setTotalQuantity(totalQuantity);
        return saleRepo.save(sale);
    }

}
