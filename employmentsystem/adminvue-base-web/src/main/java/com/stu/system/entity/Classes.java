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
@TableName("classes")
@ApiModel(value="classes对象", description="专业表")
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "")
    private String cname;

    @ApiModelProperty(value = "")
    private String cheadmaster;

    @ApiModelProperty(value = "")
    private String ctel;

    @ApiModelProperty(value = "")
    private int cnums;

    @ApiModelProperty(value = "")
    private int ctotalnums;

    @ApiModelProperty(value = "")
    private String crates;

}
