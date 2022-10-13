package com.org.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.org.pojo.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    SysUser selectByName(String userName);

}
