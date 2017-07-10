package com.project.entity;


/**
 * 角色具有的资源
 */
public class RoleAuthority {

    /**  角色名  **/
    private String rolename;

    /**  角色具有的资源  **/
    private String authorityname;

    public String getRolename() {
        return rolename;
    }

    public RoleAuthority() {
    }

    public RoleAuthority(String rolename, String authorityname) {
        this.rolename = rolename;
        this.authorityname = authorityname;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getAuthorityname() {
        return authorityname;
    }

    public void setAuthorityname(String authorityname) {
        this.authorityname = authorityname == null ? null : authorityname.trim();
    }

    @Override
    public String toString() {
        return "RoleAuthority{" +
                "rolename='" + rolename + '\'' +
                ", authorityname='" + authorityname + '\'' +
                '}';
    }
}