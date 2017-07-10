package com.project.entity;

import java.io.Serializable;

/**
 * Created by qiaowentao on 2016/10/31.
 */
public class OnlyOneUser implements Serializable {

    private static final long serialVersionUID = 176123986120812093L;

    private String phone;

    private String utr;

    private int syzt = 0;

    public OnlyOneUser(){

    }

    public OnlyOneUser(String phone){
        this.phone = phone;
    }

    public OnlyOneUser(String phone, String utr) {
        this.phone = phone;
        this.utr = utr;
    }

    public OnlyOneUser(String phone, String utr, int syzt) {
        this.phone = phone;
        this.utr = utr;
        this.syzt = syzt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUtr() {
        return utr;
    }

    public void setUtr(String utr) {
        this.utr = utr;
    }

    public int getSyzt() {
        return syzt;
    }

    public void setSyzt(int syzt) {
        this.syzt = syzt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OnlyOneUser that = (OnlyOneUser) o;

        if (syzt != that.syzt) return false;
        if (!phone.equals(that.phone)) return false;
        return utr.equals(that.utr);

    }

    @Override
    public int hashCode() {
        int result = phone.hashCode();
        result = 31 * result + utr.hashCode();
        result = 31 * result + syzt;
        return result;
    }

    @Override
    public String toString() {
        return "OnlyOneUser{" +
                "phone='" + phone + '\'' +
                ", uTr='" + utr + '\'' +
                ", syzt='" + syzt + '\'' +
                '}';
    }
}
