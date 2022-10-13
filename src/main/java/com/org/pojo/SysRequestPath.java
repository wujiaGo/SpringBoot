package com.org.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 请求路径
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRequestPath对象", description="请求路径")
public class SysRequestPath implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "请求路径")
    private String url;

    @ApiModelProperty(value = "路径描述")
    private String description;


}
