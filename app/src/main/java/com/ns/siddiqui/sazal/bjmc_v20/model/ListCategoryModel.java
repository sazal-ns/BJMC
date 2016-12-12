package com.ns.siddiqui.sazal.bjmc_v20.model;

/**
 * Created by sazal on 2016-12-04.
 */

public class ListCategoryModel {
    private  String id;
    private  String cateory_name;

    public String getCateory_name() {
        return cateory_name;
    }

    public void setCateory_name(String cateory_name) {
        this.cateory_name = cateory_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return cateory_name;
    }
}

