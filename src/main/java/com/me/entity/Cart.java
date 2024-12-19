package com.me.entity;

import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.*;

import java.util.*;


@Data
@ApiModel("购物车表")
public class Cart implements Serializable {
    private static final long serialVersionUID = -59024873863828245L;
    /**
     * 购物车ID
     */
    @ApiModelProperty("购物车ID")
    private Integer id;

    /**
     * 产品ID
     */
    @ApiModelProperty("产品ID")
    private Integer pid;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer count;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer uid;

    public Cart() {
    }

    public Cart(int id) {
        this.id = id;
    }

    public Cart(Integer id, Integer pid, Integer count, Integer uid) {
        this.id = id;
        this.pid = pid;
        this.count = count;
        this.uid = uid;
    }
}

