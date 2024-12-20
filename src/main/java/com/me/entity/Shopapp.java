package com.me.entity;

import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.*;

import java.util.*;


@Data
@ApiModel("店铺申请表")
public class Shopapp implements Serializable {
    private static final long serialVersionUID = 898972398260423554L;
    /**
     * 申请ID
     */
    @ApiModelProperty("申请ID")
    private Integer id;

    /**
     * 申请人ID
     */
    @ApiModelProperty("申请人ID")
    private Integer uid;

    /**
     * 申请内容
     */
    @ApiModelProperty("申请内容")
    private String content;

    /**
     * 是否通过审核
     */
    @ApiModelProperty("是否通过审核")
    private Integer isPass;

    public Shopapp() {
    }

    public Shopapp(int id) {
        this.id = id;
    }

    public Shopapp(Integer id, Integer uid, String content, Integer isPass) {
        this.id = id;
        this.uid = uid;
        this.content = content;
        this.isPass = isPass;
    }
}

