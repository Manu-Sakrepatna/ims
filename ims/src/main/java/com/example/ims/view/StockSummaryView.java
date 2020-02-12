package com.example.ims.view;

import com.example.ims.utils.ProductUnit;

public class StockSummaryView {

    private int product_id;
    private String name;
    private String hsnCode;
    private float quantity;
    private ProductUnit unit;
    private float price;
    private String measure;
    private int currentStockCount;

    public StockSummaryView() {
    }


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getCurrentStockCount() {
        return currentStockCount;
    }

    public void setCurrentStockCount(int currentStockCount) {
        this.currentStockCount = currentStockCount;
    }
}
