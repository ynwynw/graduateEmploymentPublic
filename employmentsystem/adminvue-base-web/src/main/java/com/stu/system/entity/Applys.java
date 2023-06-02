package com.stu.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("applys")
@ApiModel(value="Applys对象", description="应聘表")
public class Applys implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "")
    private String rname;

    @ApiModelProperty(value = "")
    private String cname;

    @ApiModelProperty(value = "")
    private String ctel;

    @ApiModelProperty(value = "")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date rdate;

    @ApiModelProperty(value = "")
    private String cattr;

    @ApiModelProperty(value = "")
    private String rintroduce;

    @ApiModelProperty(value = "")
    private String cmajor;

    @ApiModelProperty(value = "")
    private String rtitle;

    @ApiModelProperty(value = "")
    private String sname;

    @ApiModelProperty(value = "")
    private String stel;

    @ApiModelProperty(value = "")
    private String smajor;

    @ApiModelProperty(value = "")
    private String scollege;

    @ApiModelProperty(value = "")
    private String sclass;

    @ApiModelProperty(value = "")
    private String tname;

    @ApiModelProperty(value = "")
    private Integer aflag;

    @ApiModelProperty(value = "")
    private String astuts;

    @ApiModelProperty(value = "")
    private Integer classid;

    @ApiModelProperty(value = "")
    private Integer marjorid;

    @ApiModelProperty(value = "")
    private Integer collegeid;
}
