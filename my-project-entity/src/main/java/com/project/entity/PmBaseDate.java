package com.project.entity;

public class PmBaseDate {

    /**
     * 主键
     */
    private Long id;

    /**
     * 键
     */
    private String dicKey;

    /**
     * 值
     */
    private String dicValue;

    /**
     * 类型描述
     */
    private String dicType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue == null ? null : dicValue.trim();
    }

    public String getDicKey() {
        return dicKey;
    }

    public void setDicKey(String dicKey) {
        this.dicKey = dicKey;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }
}