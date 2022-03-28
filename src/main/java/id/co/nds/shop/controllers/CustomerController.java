package id.co.nds.shop.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.DeletingById;
import id.co.nds.shop.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.shop.controllers.ControllerGroup.RequestMethodById;
import id.co.nds.shop.entities.CustomerEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.models.CustomerModel;
import id.co.nds.shop.models.ResponseModel;
import id.co.nds.shop.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postCustomerController(
            @Validated(PostingNew.class) @RequestBody CustomerModel customerModel)
            throws ClientException {
        // request
        CustomerEntity customer = customerService.add(customerModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("New customer is successfully added");
        response.setData(customer);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<ResponseModel> getAllCustomersController() {
        // request
        List<CustomerEntity> customers = customerService.findAll();

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/search")
    public ResponseEntity<ResponseModel> searchCustomersController(
            @Validated(GettingAllByCriteria.class) @RequestBody CustomerModel customerModel) {
        // request
        List<CustomerEntity> customers = customerService.findAllByCriteria(customerModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getCustomerByIdController(
            @Validated(DeletingById.class) @RequestBody CustomerModel customerModel) {
        // request
        CustomerEntity customer = customerService.findById(customerModel.getId());

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(customer);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putCustomerController(
            @Validated(RequestMethodById.class) @RequestBody CustomerModel customerModel)
            throws NoSuchElementException {
        // request
        CustomerEntity customer = customerService.edit(customerModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Customer is successfully updated");
        response.setData(customer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel> deleteCustomerController(
            @Validated(DeletingById.class) @RequestBody CustomerModel customerModel) {
        // request
        CustomerEntity customer = customerService.delete(customerModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Customer is successfully deleted");
        response.setData(customer);
        return ResponseEntity.ok(response);

    }
}
