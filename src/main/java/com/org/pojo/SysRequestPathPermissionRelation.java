package com.org.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 路径权限关联表
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRequestPathPermissionRelation对象", description="路径权限关联表")
public class SysRequestPathPermissionRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "请求路径id")
    private Integer urlId;

    @ApiModelProperty(value = "权限id")
    private Integer permissionId;


}
