package com.stu.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("companys")
@ApiModel(value="Companys对象", description="公司企业表")
public class Companys implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "")
    private String cname;

    @ApiModelProperty(value = "")
    private String cpwd;

    @ApiModelProperty(value = "")
    private String cimg;

    @ApiModelProperty(value = "")
    private String ctel;

    @ApiModelProperty(value = "")
    private String cattr;

    @ApiModelProperty(value = "")
    private String cmajor;

    @ApiModelProperty(value = "")
    private String cboss;

    @ApiModelProperty(value = "")
    private String cstaffnums;

    @ApiModelProperty(value = "")
    private String imgtype;

    @ApiModelProperty(value = "")
    private Integer cflag;

    @ApiModelProperty(value = "")
    private String cstatus;

    @ApiModelProperty(value = "")
    private String cshen;

    @ApiModelProperty(value = "")
    private int cnums;
}
