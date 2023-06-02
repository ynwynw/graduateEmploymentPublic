package com.stu.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("recruits")
@ApiModel(value="recruits对象", description="岗位表")
@Getter
@Setter
public class Recruits implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "")
    private Integer rcid;

    @ApiModelProperty(value = "")
    private String rwant;

    @ApiModelProperty(value = "")
    private String rwage;

    @ApiModelProperty(value = "")
    private String rintroduce;

    @ApiModelProperty(value = "")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date rdate;

    @ApiModelProperty(value = "")
    private String rnums;

    @ApiModelProperty(value = "")
    private String rtitle;

    @ApiModelProperty(value = "")
    private String cname;

    @ApiModelProperty(value = "")
    private String ctel;

    @ApiModelProperty(value = "")
    private String cattrs;

    @ApiModelProperty(value = "")
    private String cmajor;

    @ApiModelProperty(value = "")
    @TableField(exist = false)
    private String resumetitle;

    @ApiModelProperty(value = "")
    private int cnums;
}
