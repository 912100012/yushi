package com.me.entity;

import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.*;

import java.util.*;


@Data
@ApiModel("用户信息表")
public class User implements Serializable {
    private static final long serialVersionUID = 615577466242642469L;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer id;

    /**
     * 用户编码
     */
    @ApiModelProperty("用户编码")
    private String code;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String psw;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nick;

    /**
     * 用户权限
     */
    @ApiModelProperty("用户权限")
    private Integer uright;

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String tel;

    /**
     * 余额
     */
    @ApiModelProperty("余额")
    private Double balance;

    /**
     * 是否是店铺
     */
    @ApiModelProperty("是否是店铺")
    private Integer isShop;

    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    private String address;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(Integer id, String code, String psw, String nick, Integer uright, String tel, Double balance, Integer isShop, String address) {
        this.id = id;
        this.code = code;
        this.psw = psw;
        this.nick = nick;
        this.uright = uright;
        this.tel = tel;
        this.balance = balance;
        this.isShop = isShop;
        this.address = address;
    }
}

