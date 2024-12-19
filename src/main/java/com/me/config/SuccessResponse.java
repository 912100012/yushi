package com.me.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@ApiModel("返回前台包装类")
public class SuccessResponse<T>  {

    @ApiModelProperty("返回数据对象")
    T data;

    @ApiModelProperty("返回提示信息")
    private String msg;

    @ApiModelProperty("返回状态码，默认200")
    private int status;

    public SuccessResponse(String msg) {
        this.msg = msg;
        this.status = HttpStatus.OK.value();
    }

    public SuccessResponse(T data) {
        this.data = data;
        this.status = HttpStatus.OK.value();
    }

    public SuccessResponse(T data, String msg) {
        this.data = data;
        this.msg = msg;
        this.status = HttpStatus.OK.value();
    }

    public SuccessResponse(T data, String msg,int status) {
        this.data = data;
        this.msg = msg;
        this.status = status;
    }

    public SuccessResponse(String msg,int status) {
        this.msg = msg;
        this.status = status;
    }
}
