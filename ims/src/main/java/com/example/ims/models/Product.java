package com.example.ims.models;

import com.example.ims.utils.CategoryType;
import com.example.ims.utils.ProductType;
import com.example.ims.utils.ProductUnit;
import com.example.ims.utils.StatusType;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity(name = "product")
@ApiModel(description = "The product registrations will be managed here.")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String manufacturer;  //This shall be changed to an enum or a reference
    private String description;
    @Column(unique = true)
    private String hsnCode;   //This will be unique for each distinguishable product either by name/size/price
    private String sku;
    private StatusType status;
    private CategoryType category;
    private ProductType type;
    private float quantity;
    private ProductUnit unit;
    private float price;
    private String measure;
    private int location;
    private int currentStockCount;
    private int lowStockCount;

    //@Column(unique = true)
    private String registration_code;


    public Product() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration_code() {
        return registration_code;
    }

    public void setRegistration_code(String registration_code) {
        this.registration_code = registration_code;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCurrentStockCount() {
        return currentStockCount;
    }

    public void setCurrentStockCount(int currentStockCount) {
        this.currentStockCount = currentStockCount;
    }

    public int getLowStockCount() {
        return lowStockCount;
    }

    public void setLowStockCount(int lowStockCount) {
        this.lowStockCount = lowStockCount;
    }
}
