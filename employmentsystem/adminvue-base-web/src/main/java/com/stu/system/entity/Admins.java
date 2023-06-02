package com.stu.system.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("admins")
@ApiModel(value="Admins对象", description="管理员表")
public class Admins implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String uname;

    @ApiModelProperty(value = "联系电话")
    private String utel;

    @ApiModelProperty(value = "密码")
    private String upwd;

}
