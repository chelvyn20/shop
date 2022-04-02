package id.co.nds.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.entities.SaleEntity;
import id.co.nds.shop.generators.ResponseGenerator;
import id.co.nds.shop.models.ResponseModel;
import id.co.nds.shop.models.SaleModel;
import id.co.nds.shop.services.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postSaleController(@RequestBody SaleModel saleModel)
            throws Exception {
        // request
        SaleEntity sale = saleService.addSale(saleModel);

        // response
        String className = sale.getClass().getSimpleName();
        return new ResponseGenerator(sale, className, PostingNew.class).getResponse();
    }
}