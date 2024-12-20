package com.me.entity;

import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.*;

import java.util.*;


@Data
@ApiModel("收藏表")
public class Favorite implements Serializable {
    private static final long serialVersionUID = 370938552415921686L;
    /**
     * 收藏ID
     */
    @ApiModelProperty("收藏ID")
    private Integer id;

    /**
     * 产品ID
     */
    @ApiModelProperty("产品ID")
    private Integer pid;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer uid;

    public Favorite() {
    }

    public Favorite(int id) {
        this.id = id;
    }

    public Favorite(Integer id, Integer pid, Integer uid) {
        this.id = id;
        this.pid = pid;
        this.uid = uid;
    }
}

