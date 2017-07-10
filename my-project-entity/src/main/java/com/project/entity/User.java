package com.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User  implements Serializable {

    private static final long serialVersionUID = 11231312123125346L;

    private Long id;

    /**  用户登录名：也即手机号  **/
    private String phone;

    /**  登录密码  **/
    private String password;

    /** 昵称  **/
    private String nicheng;

    /**  性别 : 0为男，1为女**/
    private Byte sex;

    /**  性别描述   **/
    private String sexStr;

    /** 账户余额  **/
    private BigDecimal money;

    private String monetStr;

    private String updtime;

    private String createtime;

    /**
     * 使用状态，0：正常，1：删除状态，不可用, 2:账号禁用
     */
    private int syzt = 0;

    private String username;

    private String address;

    private String email;

    /**  盐值，用户密码加密  **/
    private String salt;

    private String yaoqingma;

    private String qq;

    public User(){
        //新建用户默认可以使用
        //this.syzt = "Y";
    }

    public User(String phone) {
        this.phone = phone;
    }

    public User(Long id, String phone, String password, String nicheng, Byte sex, BigDecimal money, String updtime, String createtime, int syzt, String username, String address, String email, String salt, String yaoqingma, String qq) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.nicheng = nicheng;
        this.sex = sex;
        this.money = money;
        this.updtime = updtime;
        this.createtime = createtime;
        this.syzt = syzt;
        this.username = username;
        this.address = address;
        this.email = email;
        this.salt = salt;
        this.yaoqingma = yaoqingma;
        this.qq = qq;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng == null ? null : nicheng.trim();
    }

    public Byte getSex() {

        return sex;
    }

    public void setSex(Byte sex) {
        if(sex != null && !"".equals(sex)){
            if(sex == 0){
                this.sexStr = "男";
            }
            if(sex == 1){
                this.sexStr = "女";
            }
        }
        this.sex = sex;
    }

    public void setSexStr(String sexStr){
        if(sexStr != null && !"".equals(sexStr)){
            if(sexStr.equals("男")){
                this.sex = 0;
            }
            if(sexStr.equals("女")){
                this.sex = 1;
            }
        }
        this.sexStr = sexStr;
    }

    public String getSexStr(){
        return sexStr;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMonetStr() {
        return monetStr;
    }

    public void setMonetStr(String monetStr) {
        if(null != monetStr && !"".equals(monetStr)){
            Double moneyDouble = 0.0;
            try{
                moneyDouble = Double.valueOf(monetStr);
            }catch(Exception e){

            }
            this.money = BigDecimal.valueOf(moneyDouble);

        }
        this.monetStr = monetStr;
    }

    public String getUpdtime() {
        return updtime;
    }

    public void setUpdtime(String updtime) {
        this.updtime = updtime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public void setSyzt(int syzt) {
        this.syzt = syzt;
    }

    public int getSyzt() {
        return syzt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setYaoqingma(String yaoqingma) {
        this.yaoqingma = yaoqingma;
    }

    public String getYaoqingma() {
        return yaoqingma;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", nicheng='" + nicheng + '\'' +
                ", sex=" + sex +
                ", money=" + money +
                ", updtime='" + updtime + '\'' +
                ", createtime='" + createtime + '\'' +
                ", syzt=" + syzt +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", yaoqingma='" + yaoqingma + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}