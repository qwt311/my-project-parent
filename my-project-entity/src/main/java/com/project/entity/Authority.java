package com.project.entity;


/**
 * 资源实体类
 */
public class Authority {

    /**  主键  **/
    private Integer id;

    /**  资源名  **/
    private String authorityname;

    public Authority() {
    }

    public Authority(String authorityname, Integer id) {
        this.authorityname = authorityname;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorityname() {
        return authorityname;
    }

    public void setAuthorityname(String authorityname) {
        this.authorityname = authorityname == null ? null : authorityname.trim();
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authorityname='" + authorityname + '\'' +
                '}';
    }
}