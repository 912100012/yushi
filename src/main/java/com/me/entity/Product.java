package com.me.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.*;

import java.util.*;


@Data
@ApiModel("珠宝产品表")
public class Product implements Serializable {
    private static final long serialVersionUID = 957856879896331266L;
    /**
     * 产品ID
     */
    @ApiModelProperty("产品ID")
    private Integer id;

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    private String name;

    /**
     * 种类ID
     */
    @ApiModelProperty("种类ID")
    private Integer tid;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private Double price;

    /**
     * 购买者ID
     */
    @ApiModelProperty("购买者ID")
    private Integer byuid;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date ctime;

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(Integer id, String name, Integer tid, Double price, Integer byuid, Date ctime) {
        this.id = id;
        this.name = name;
        this.tid = tid;
        this.price = price;
        this.byuid = byuid;
        this.ctime = ctime;
    }
}

