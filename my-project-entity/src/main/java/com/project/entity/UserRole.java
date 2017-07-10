package com.project.entity;


import java.util.List;

/**
 * 用户角色实体
 */
public class UserRole {

    /**  用户  **/
    private String phone;

    /**  用户具有的角色  **/
    private String rolename;

    public String getPhone() {
        return phone;
    }

    private List<RoleAuthority> roleAuthorities;

    public UserRole() {
    }

    public UserRole(String phone, String rolename) {
        this.phone = phone;
        this.rolename = rolename;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public List<RoleAuthority> getRoleAuthorities() {
        return roleAuthorities;
    }

    public void setRoleAuthorities(List<RoleAuthority> roleAuthorities) {
        this.roleAuthorities = roleAuthorities;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "phone='" + phone + '\'' +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}