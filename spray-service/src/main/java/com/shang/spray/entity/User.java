package com.shang.spray.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class User extends BaseEntity{


    /**
     * 用户名
     */
    private String username;

    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 账号状态（正常，锁定，删除）
     */
    private Integer status;


    public enum ROLE{
        admin, user;
    }

    @Enumerated(EnumType.STRING)
    private ROLE role;

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密码盐
     *
     * @return salt - 密码盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置密码盐
     *
     * @param salt 密码盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取账号状态（正常，锁定，删除）
     *
     * @return status - 账号状态（正常，锁定，删除）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置账号状态（正常，锁定，删除）
     *
     * @param status 账号状态（正常，锁定，删除）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}