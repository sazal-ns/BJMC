package com.ns.siddiqui.sazal.bjmc_v20.model;

/**
 * Created by sazal on 2016-12-04.
 */

public class ListItemModel {

    private  String id;
    private  String item_name;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    @Override
    public String toString() {
        return item_name;
    }
}
