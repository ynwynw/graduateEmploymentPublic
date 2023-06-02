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
@TableName("resumes")
@ApiModel(value="resumes对象", description="简历表")
public class Resumes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "")
    private Integer ruid;

    @ApiModelProperty(value = "")
    private String rworks;

    @ApiModelProperty(value = "")
    private String redus;

    @ApiModelProperty(value = "")
    private String rwantworks;

    @ApiModelProperty(value = "")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date rdate;

    @ApiModelProperty(value = "")
    private String rwages;

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
}
