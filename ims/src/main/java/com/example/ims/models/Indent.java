package com.example.ims.models;

import com.example.ims.utils.IndentType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "indent")
public class Indent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private IndentType type;

    private Date indent_date;

    private Float total_price;

    @OneToMany(mappedBy = "indent")
    //@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    private Set<IndentItem> indent_items;

   //Need to establish a relation with customer
   //Need to establish a relation with location
   //private Location location_id -- Need to establosh a table relation
   @ManyToMany(fetch = FetchType.LAZY,
           cascade = {
                   CascadeType.PERSIST,
                   CascadeType.MERGE
           })
   @JoinTable(name = "indent_location",
           joinColumns = { @JoinColumn(name = "indent_id") },
           inverseJoinColumns = { @JoinColumn(name = "location_id") })
   private Set<Location> locations = new HashSet<>();


    public Indent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IndentType getType() {
        return type;
    }

    public void setType(IndentType type) {
        this.type = type;
    }

    public Date getIndent_date() {
        return indent_date;
    }

    public void setIndent_date(Date indent_date) {
        this.indent_date = indent_date;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public Set<IndentItem> getIndent_items() {
        return indent_items;
    }

    public void setIndent_items(Set<IndentItem> indent_items) {
        this.indent_items = indent_items;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
}
