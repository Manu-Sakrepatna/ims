package com.example.ims.models;

import com.example.ims.utils.CustomerType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity(name = "customer")
@ApiModel(description = "The customer (either Client or Vendor) details are maintained here.")

public class Customer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty(notes = "Client/Vendor name is represented here.")
    @Column(unique = true)
    private String name;
    private CustomerType type;
    private boolean isBlocked = false;
    private String address;
    @Column(name = "contact_number")
    private String contactNumber;
    private String pan;
    private String gst;

    public Customer() {
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

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }
}
