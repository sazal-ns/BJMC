package com.ns.siddiqui.sazal.bjmc_v20.model;

/**
 * Created by sazal on 2016-12-10.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("txtgred")
    @Expose
    private String txtgred;
    @SerializedName("txtpercentage")
    @Expose
    private String txtpercentage;
    @SerializedName("txtacquantity")
    @Expose
    private String txtacquantity;
    @SerializedName("txtvalue")
    @Expose
    private String txtvalue;
    @SerializedName("txtrate")
    @Expose
    private String txtrate;

    /**
     *
     * @return
     * The txtgred
     */
    public String getTxtgred() {
        return txtgred;
    }

    /**
     *
     * @param txtgred
     * The txtgred
     */
    public void setTxtgred(String txtgred) {
        this.txtgred = txtgred;
    }

    /**
     *
     * @return
     * The txtpercentage
     */
    public String getTxtpercentage() {
        return txtpercentage;
    }

    /**
     *
     * @param txtpercentage
     * The txtpercentage
     */
    public void setTxtpercentage(String txtpercentage) {
        this.txtpercentage = txtpercentage;
    }

    /**
     *
     * @return
     * The txtacquantity
     */
    public String getTxtacquantity() {
        return txtacquantity;
    }

    /**
     *
     * @param txtacquantity
     * The txtacquantity
     */
    public void setTxtacquantity(String txtacquantity) {
        this.txtacquantity = txtacquantity;
    }

    /**
     *
     * @return
     * The txtvalue
     */
    public String getTxtvalue() {
        return txtvalue;
    }

    /**
     *
     * @param txtvalue
     * The txtvalue
     */
    public void setTxtvalue(String txtvalue) {
        this.txtvalue = txtvalue;
    }

    /**
     *
     * @return
     * The txtrate
     */
    public String getTxtrate() {
        return txtrate;
    }

    /**
     *
     * @param txtrate
     * The txtrate
     */
    public void setTxtrate(String txtrate) {
        this.txtrate = txtrate;
    }


}
