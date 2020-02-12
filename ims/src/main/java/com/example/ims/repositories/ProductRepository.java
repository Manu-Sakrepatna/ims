package com.example.ims.repositories;

import com.example.ims.models.Product;
import com.example.ims.utils.ProductUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductById(Integer id);


    @Query("SELECT p from product p where p.name = ?1 and  p.quantity = ?2 and p.unit = ?3 and p.price = ?4")
    List<Product> getUniqueProduct(String name, float quantity, ProductUnit unit, float price);


    @Query("SELECT p from product p where p.currentStockCount <= p.lowStockCount")
    List<Product> lowStockSummary();



}
