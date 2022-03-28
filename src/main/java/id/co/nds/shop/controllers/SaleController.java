package id.co.nds.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.shop.entities.SaleEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.models.ResponseModel;
import id.co.nds.shop.models.SaleModel;
import id.co.nds.shop.services.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postSaleController(
            @RequestBody SaleModel saleModel) {
        try {
            // request
            SaleEntity sale = saleService.add(saleModel);
            sale = saleService.addSaleProductList(sale, saleModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setMsg("New sale is successfully added");
            response.setData(sale);
            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }
}