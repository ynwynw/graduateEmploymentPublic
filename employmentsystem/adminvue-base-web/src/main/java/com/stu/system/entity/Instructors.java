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
@TableName("instructors")
@ApiModel(value="Instructors对象", description="辅导员表")
public class Instructors implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "")
    private String sname;

    @ApiModelProperty(value = "")
    private String spwd;

    @ApiModelProperty(value = "")
    private String simg;

    @ApiModelProperty(value = "")
    private String stel;

    @ApiModelProperty(value = "")
    private String scollege;

    @ApiModelProperty(value = "")
    private String smajor;

    @ApiModelProperty(value = "")
    private String sclass;

    @ApiModelProperty(value = "")
    private String imgtype;

    @ApiModelProperty(value = "")
    private Integer ssex;

    @ApiModelProperty(value = "")
    private Integer sage;

    @ApiModelProperty(value = "")
    private Integer classid;

    @ApiModelProperty(value = "")
    private Integer marjorid;

    @ApiModelProperty(value = "")
    private Integer collegeid;
}
