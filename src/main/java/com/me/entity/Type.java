package com.me.entity;

import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.*;

import java.util.*;


@Data
@ApiModel("珠宝产品种类表")
public class Type implements Serializable {
    private static final long serialVersionUID = 517586543446531944L;
    /**
     * 种类ID
     */
    @ApiModelProperty("种类ID")
    private Integer id;

    /**
     * 种类名称
     */
    @ApiModelProperty("种类名称")
    private String type;

    public Type() {
    }

    public Type(int id) {
        this.id = id;
    }

    public Type(Integer id, String type) {
        this.id = id;
        this.type = type;
    }
}

