package com.org.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser对象", description="用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "上一次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "账号是否可用。默认为1（可用）")
    private Boolean enabled;

    @ApiModelProperty(value = "是否过期。默认为1（没有过期）")
    private Boolean notExpired;

    @ApiModelProperty(value = "账号是否锁定。默认为1（没有锁定）")
    private Boolean accountNotLocked;

    @ApiModelProperty(value = "证书（密码）是否过期。默认为1（没有过期）")
    private Boolean credentialsNotExpired;

    @TableField(fill = FieldFill.INSERT, value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE, value = "update_time")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    @ApiModelProperty(value = "修改人")
    private Integer updateUser;


}
