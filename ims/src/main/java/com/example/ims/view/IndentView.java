package com.example.ims.view;

import com.example.ims.models.IndentItem;
import com.example.ims.utils.IndentType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class IndentView {
    private int id;

    private IndentType type;

    private Date indent_date;

    private Float total_price;

    private Set<IndentItemView> indent_item_views;

    private int[] location_ids;



    public IndentView() {
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

    public Set<IndentItemView> getIndent_item_views() {
        return indent_item_views;
    }

    public void setIndent_item_views(Set<IndentItemView> indent_item_views) {
        this.indent_item_views = indent_item_views;
    }

    public int[] getLocation_ids() {
        return location_ids;
    }

    public void setLocation_ids(int[] location_id) {
        this.location_ids = location_id;
    }
}
