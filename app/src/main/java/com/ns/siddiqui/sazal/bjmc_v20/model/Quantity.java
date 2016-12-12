package com.ns.siddiqui.sazal.bjmc_v20.model;

/**
 * Created by sazal on 2016-12-10.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quantity {

    @SerializedName("txtquantity")
    @Expose
    private String txtquantity;
    @SerializedName("txtquantityKg")
    @Expose
    private String txtquantityKg;
    @SerializedName("txtquantityMn")
    @Expose
    private String txtquantityMn;

    /**
     *
     * @return
     * The txtquantity
     */
    public String getTxtquantity() {
        return txtquantity;
    }

    /**
     *
     * @param txtquantity
     * The txtquantity
     */
    public void setTxtquantity(String txtquantity) {
        this.txtquantity = txtquantity;
    }

    /**
     *
     * @return
     * The txtquantityKg
     */
    public String getTxtquantityKg() {
        return txtquantityKg;
    }

    /**
     *
     * @param txtquantityKg
     * The txtquantityKg
     */
    public void setTxtquantityKg(String txtquantityKg) {
        this.txtquantityKg = txtquantityKg;
    }

    /**
     *
     * @return
     * The txtquantityMn
     */
    public String getTxtquantityMn() {
        return txtquantityMn;
    }

    /**
     *
     * @param txtquantityMn
     * The txtquantityMn
     */
    public void setTxtquantityMn(String txtquantityMn) {
        this.txtquantityMn = txtquantityMn;
    }


}
