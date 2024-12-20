package com.me.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.*;

import java.util.*;


@Data
@ApiModel("历史购买订单表")
public class Order implements Serializable {
    private static final long serialVersionUID = -44225177732336287L;
    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
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

    /**
     * 购买时间
     */
    @ApiModelProperty("购买时间")
    private Date ctime;

    public Order() {
    }

    public Order(int id) {
        this.id = id;
    }

    public Order(Integer id, Integer pid, Integer count, Integer uid, Date ctime) {
        this.id = id;
        this.pid = pid;
        this.count = count;
        this.uid = uid;
        this.ctime = ctime;
    }
}

