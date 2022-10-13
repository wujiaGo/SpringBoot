package com.org.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.org.pojo.SysPermission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 查询用户的权限列表
     *
     * @param userId
     * @return
     */
    List<SysPermission> selectListByUser(Integer userId);

    /**
     * 查询具体某个接口的权限
     *
     * @param path
     * @return
     */
    List<SysPermission> selectListByPath(String path);
}
