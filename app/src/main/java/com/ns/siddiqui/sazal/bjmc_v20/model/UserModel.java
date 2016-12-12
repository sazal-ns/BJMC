package com.ns.siddiqui.sazal.bjmc_v20.model;

import java.io.Serializable;

/**
 * Created by sazal on 2016-12-04.
 */

public class UserModel implements Serializable{
    private static String status;
    private static boolean error;
    private static String token;
    private static int token_expire;

    private static String id;
    private static String user_name;
    private static String mill_id;
    private static String mill_name;
    private static String short_name;
    private static String purchase_point_id;
    private static String pp_name;
    private static String buyer_id;
    private static String unit_id;

    public UserModel() {
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        UserModel.status = status;
    }

    public static boolean isError() {
        return error;
    }

    public static void setError(boolean error) {
        UserModel.error = error;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        UserModel.token = token;
    }

    public static int getToken_expire() {
        return token_expire;
    }

    public static void setToken_expire(int token_expire) {
        UserModel.token_expire = token_expire;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        UserModel.id = id;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_name(String user_name) {
        UserModel.user_name = user_name;
    }

    public static String getMill_id() {
        return mill_id;
    }

    public static void setMill_id(String mill_id) {
        UserModel.mill_id = mill_id;
    }

    public static String getMill_name() {
        return mill_name;
    }

    public static void setMill_name(String mill_name) {
        UserModel.mill_name = mill_name;
    }

    public static String getShort_name() {
        return short_name;
    }

    public static void setShort_name(String short_name) {
        UserModel.short_name = short_name;
    }

    public static String getPurchase_point_id() {
        return purchase_point_id;
    }

    public static void setPurchase_point_id(String purchase_point_id) {
        UserModel.purchase_point_id = purchase_point_id;
    }

    public static String getPp_name() {
        return pp_name;
    }

    public static void setPp_name(String pp_name) {
        UserModel.pp_name = pp_name;
    }

    public static String getBuyer_id() {
        return buyer_id;
    }

    public static void setBuyer_id(String buyer_id) {
        UserModel.buyer_id = buyer_id;
    }

    public static String getUnit_id() {
        return unit_id;
    }

    public static void setUnit_id(String unit_id) {
        UserModel.unit_id = unit_id;
    }
}
