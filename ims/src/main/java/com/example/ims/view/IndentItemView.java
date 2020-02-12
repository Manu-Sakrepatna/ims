package com.example.ims.view;

import com.example.ims.models.Indent;



public class IndentItemView {

    private int id;

    private int product_id; //TBD: Need to establish relation with inventory table that has product details
    private int quantity;
    private float price;
    private int indent_id;

    public IndentItemView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIndent_id() {
        return indent_id;
    }

    public void setIndent_id(int indent_id) {
        this.indent_id = indent_id;
    }
}
