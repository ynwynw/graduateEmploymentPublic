package com.stu.system.entity;


import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.rowset.internal.BaseRow;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("users")
@ApiModel(value="Users对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String sname;

    @ApiModelProperty(value = "密码")
    private String spwd;

    @ApiModelProperty(value = "头像")
    private String simg;

    @ApiModelProperty(value = "联系电话")
    private String stel;

    @ApiModelProperty(value = "所在学院")
    private String scollege;

    @ApiModelProperty(value = "所在专业")
    private String smajor;

    @ApiModelProperty(value = "所在班级")
    private String sclass;

    @ApiModelProperty(value = "图片类型")
    private String imgtype;

    @ApiModelProperty(value = "性别 1男 2女")
    private Integer ssex;

    @ApiModelProperty(value = "")
    private String status;

    @ApiModelProperty(value = "")
    private Integer sage;

    @ApiModelProperty(value = "")
    private Integer sflag;

    @ApiModelProperty(value = "")
    private Integer classid;

    @ApiModelProperty(value = "")
    private Integer marjorid;

    @ApiModelProperty(value = "")
    private Integer collegeid;

    @ApiModelProperty(value = "")
    private String tripartiteagreement;

    @ApiModelProperty(value = "")
    private String imgtype2;
}
