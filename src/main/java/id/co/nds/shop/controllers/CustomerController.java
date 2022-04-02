package id.co.nds.shop.controllers;

import java.util.List;

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
import id.co.nds.shop.controllers.ControllerGroup.GettingAll;
import id.co.nds.shop.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.shop.controllers.ControllerGroup.GettingById;
import id.co.nds.shop.controllers.ControllerGroup.UpdatingById;
import id.co.nds.shop.entities.CustomerEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.generators.ResponseGenerator;
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
        String className = customer.getClass().getSimpleName();
        return new ResponseGenerator(customer, className, PostingNew.class).getResponse();
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<ResponseModel> getAllCustomersController() {
        // request
        List<CustomerEntity> customers = customerService.findAll();

        // response
        String className = customers.get(0).getClass().getSimpleName();
        return new ResponseGenerator(customers, className, GettingAll.class).getResponse();
    }

    @GetMapping(value = "/get/search")
    public ResponseEntity<ResponseModel> searchCustomersController(
            @Validated(GettingAllByCriteria.class) @RequestBody CustomerModel customerModel) {
        // request
        List<CustomerEntity> customers = customerService.findAllByCriteria(customerModel);

        // response
        String className = customers.get(0).getClass().getSimpleName();
        return new ResponseGenerator(customers, className, GettingAllByCriteria.class)
                .getResponse();
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getCustomerByIdController(
            @Validated(GettingById.class) @RequestBody CustomerModel customerModel) {
        // request
        CustomerEntity customer = customerService.findById(customerModel.getId());

        // response
        String className = customer.getClass().getSimpleName();
        return new ResponseGenerator(customer, className, GettingById.class).getResponse();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putCustomerController(
            @Validated(UpdatingById.class) @RequestBody CustomerModel customerModel) {
        // request
        CustomerEntity customer = customerService.edit(customerModel);

        // response
        String className = customer.getClass().getSimpleName();
        return new ResponseGenerator(customer, className, UpdatingById.class).getResponse();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel> deleteCustomerController(
            @Validated(DeletingById.class) @RequestBody CustomerModel customerModel)
            throws ClientException {
        // request
        CustomerEntity customer = customerService.delete(customerModel);

        // response
        String className = customer.getClass().getSimpleName();
        return new ResponseGenerator(customer, className, DeletingById.class).getResponse();
    }
}
