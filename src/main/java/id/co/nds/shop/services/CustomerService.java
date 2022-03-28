package id.co.nds.shop.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.shop.entities.CustomerEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.generators.DateGenerator;
import id.co.nds.shop.globals.GlobalConstant;
import id.co.nds.shop.models.CustomerModel;
import id.co.nds.shop.repos.CustomerRepo;
import id.co.nds.shop.repos.specs.CustomerSpec;

@Service
public class CustomerService implements Serializable {
    @Autowired
    private CustomerRepo customerRepo;

    public CustomerEntity add(CustomerModel customerModel) throws ClientException {
        Long count = customerRepo.countByName(customerModel.getName());
        if (count > 0) {
            throw new ClientException("Customer name is already existed");
        }

        CustomerEntity customer = new CustomerEntity();
        customer.setName(customerModel.getName());
        customer.setCallNumber(customerModel.getCallNumber());
        customer.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        customer.setCreatedTime(DateGenerator.generateTimestamp());
        customer.setCreatedBy(
                customerModel.getActorId() == null ? 0 : customerModel.getActorId());

        return customerRepo.save(customer);

    }

    public List<CustomerEntity> findAll() {
        List<CustomerEntity> customers = new ArrayList<>();
        customerRepo.findAll().forEach(customers::add);

        return customers;
    }

    public List<CustomerEntity> findAllByCriteria(CustomerModel customerModel) {
        List<CustomerEntity> customers = new ArrayList<>();
        CustomerSpec specs = new CustomerSpec(customerModel);
        customerRepo.findAll(specs).forEach(customers::add);

        return customers;
    }

    public CustomerEntity findById(String id) {
        CustomerEntity customer = customerRepo.findById(id).orElseThrow();

        return customer;
    }

    public CustomerEntity edit(CustomerModel customerModel) {
        CustomerEntity customer = new CustomerEntity();
        customer = findById(customerModel.getId());

        if (customerModel.getName() != null) {
            customer.setName(customerModel.getName());
        }

        if (customerModel.getCallNumber() != null) {
            customer.setCallNumber(customerModel.getCallNumber());
        }

        customer.setUpdatedTime(DateGenerator.generateTimestamp());
        customer.setUpdatedBy(
                customerModel.getActorId() == null ? 0 : customerModel.getActorId());

        return customerRepo.save(customer);
    }

    public CustomerEntity delete(CustomerModel customerModel) {
        CustomerEntity customer = new CustomerEntity();
        customer = findById(customerModel.getId());
        customer.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        customer.setDeletedTime(DateGenerator.generateTimestamp());
        customer.setDeletedBy(
                customerModel.getActorId() == null ? 0 : customerModel.getActorId());

        return customerRepo.save(customer);
    }
}
