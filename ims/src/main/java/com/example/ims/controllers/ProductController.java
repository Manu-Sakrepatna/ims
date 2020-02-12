package com.example.ims.controllers;

import com.example.ims.ImsApplication;
import com.example.ims.models.Product;
import com.example.ims.repositories.ProductRepository;
import com.example.ims.service.ProductService;
import com.example.ims.utils.ProductUnit;
import com.example.ims.view.StockSummaryView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public List<Product> list(){
        logger.info("Inside the Get Request to list objects");
        return this.productRepository.findAll();
    }

    //@GetMapping
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Product get(@PathVariable int id) {

        logger.info("Inside the Get Request to fetch details of an object");
        Product dbProduct = productRepository.findById(id).orElse(null);
        if (dbProduct != null)
            return dbProduct;
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }

    }

    @PostMapping
    public Product create(@RequestBody final Product product) {
        logger.info("Inside Post Request to create a new object");
        return productRepository.saveAndFlush(product);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Product update(@PathVariable int id, @RequestBody Product product) {
        logger.info("Inside the Put Request to update an object");
        Product dbProduct = productRepository.findById(id).orElse(null);
        if (dbProduct != null) {
            BeanUtils.copyProperties(product, dbProduct, "id");
            return productRepository.saveAndFlush(dbProduct);
        }
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }

    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        logger.info("Inside the Delete Request to delete an object");
        if (productRepository.findById(id).orElse(null) != null)
            productRepository.deleteById(id)   ;
        else
            logger.info("Cannot find the object with the given id");
    }


    //   .../fetchProductByDetails?name="name1"&quantity=10&unit="PU1"&price=10.5
    @RequestMapping(value = "fetchProductByDetails", method = RequestMethod.GET)
    public ResponseEntity<Product> fetchProductByDetails(@RequestParam String name,
                                                         @RequestParam float quantity,
                                                         @RequestParam ProductUnit unit,
                                                         @RequestParam float price){

        logger.info("Inside the GET Request to fetchProductByDetails");
        Product dbProduct = productRepository.getUniqueProduct(name, quantity, unit, price).get(0);
        if (dbProduct == null){
            logger.info("Cannot find the object with the given details");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dbProduct);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(dbProduct);
        }
    }




    @RequestMapping(value = "getProductStock/{id}", method = RequestMethod.GET)
    public StockSummaryView getProductStock(@PathVariable int id) {

        logger.info("Inside the Get Request to fetch stock details of an object");
        StockSummaryView dbProduct = productService.getProductStock(id);
        if (dbProduct != null)
            return dbProduct;
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }
    }




    @RequestMapping(value = "getStockSummary", method = RequestMethod.GET)
    public List<StockSummaryView> getStockSummary() {

        logger.info("Inside the Get Request to fetch current stock details of all products");
        List<StockSummaryView> summaryList = productService.getStockSummary();
        return summaryList;
    }



    @RequestMapping(value = "getLowStockSummary", method = RequestMethod.GET)
    public List<StockSummaryView> getLowStockSummary() {

        logger.info("Inside the Get Request to fetch current LOW stock details");
        List<StockSummaryView> summaryList = productService.getLowStockSummary();
        return summaryList;
    }



}
