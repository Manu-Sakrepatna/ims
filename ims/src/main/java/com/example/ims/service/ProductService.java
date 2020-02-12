package com.example.ims.service;

import com.example.ims.models.Product;
import com.example.ims.repositories.ProductRepository;
import com.example.ims.utils.ProductUnit;
import com.example.ims.view.StockSummaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product fetchProductOnId(int id){
        Product product = productRepository.findProductById(Integer.valueOf(id));
        return product;
    }

    public Product updateProduct(Product product){
        return productRepository.saveAndFlush(product);
    }


    public Product getUniqueProduct(String name, float quantity, ProductUnit unit, float price){
        Product dbProduct = productRepository.getUniqueProduct(name, quantity, unit, price).get(0);
        return dbProduct;
    }



    public StockSummaryView getProductStock(int id){
        Product product = productRepository.findProductById(Integer.valueOf(id));
        if (product != null) {
            return mapProductToStockSummaryView(product);
        }else{
            return null;
        }
    }



    public List<StockSummaryView> getStockSummary(){

        List<Product> productList = productRepository.findAll();
        List<StockSummaryView> stockList = new ArrayList<>() ;

        for(Product product: productList){
            stockList.add(mapProductToStockSummaryView(product));
        }

        return stockList;
    }


    public List<StockSummaryView> getLowStockSummary(){
        List<Product> productList = productRepository.lowStockSummary();
        List<StockSummaryView> stockList = new ArrayList<>() ;

        for(Product product: productList){
            stockList.add(mapProductToStockSummaryView(product));
        }

        return stockList;
    }


    public StockSummaryView mapProductToStockSummaryView(Product product){
        StockSummaryView newSummaryItem = new StockSummaryView();
        newSummaryItem.setProduct_id(product.getId());
        newSummaryItem.setHsnCode(product.getHsnCode());
        newSummaryItem.setName(product.getName());
        newSummaryItem.setMeasure(product.getMeasure());
        newSummaryItem.setQuantity(product.getQuantity());
        newSummaryItem.setUnit(product.getUnit());
        newSummaryItem.setPrice(product.getPrice());
        newSummaryItem.setCurrentStockCount(product.getCurrentStockCount());

        return newSummaryItem;
    }

}
