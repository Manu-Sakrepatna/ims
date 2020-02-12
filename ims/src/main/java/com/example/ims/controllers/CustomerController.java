package com.example.ims.controllers;


import com.example.ims.models.Customer;
import com.example.ims.models.Product;
import com.example.ims.repositories.CustomerRepository;
import com.example.ims.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomerController {
    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("list")
    public List<Customer> list(){
        logger.info("Inside the Get Request to list objects");
        return this.customerRepository.findAll();
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Customer get(@PathVariable int id) {

        logger.info("Inside the Get Request to fetch details of an object");
        Customer dbCustomer = customerRepository.findById(id).orElse(null);
        if (dbCustomer != null)
            return dbCustomer;
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }
    }



    @PostMapping
    public Customer create(@RequestBody final Customer customer) {
        logger.info("Inside Post Request to create a new object");
        return customerRepository.saveAndFlush(customer);
    }



    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Customer update(@PathVariable int id, @RequestBody Customer customer) {
        logger.info("Inside the Put Request to update an object");
        Customer dbCustomer = customerRepository.findById(id).orElse(null);
        if (dbCustomer != null) {
            BeanUtils.copyProperties(customer, dbCustomer, "id");
            return customerRepository.saveAndFlush(dbCustomer);
        }
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        logger.info("Inside the Delete Request to delete an object");
        if (customerRepository.findById(id).orElse(null) != null)
            customerRepository.deleteById(id)   ;
        else
            logger.info("Cannot find the object with the given id");
    }

}
