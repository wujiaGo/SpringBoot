package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.mapper.SysPermissionMapper;
import com.org.pojo.SysPermission;
import com.org.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> selectListByUser(Integer userId) {
        return sysPermissionMapper.selectListByUser(userId);
    }

    @Override
    public List<SysPermission> selectListByPath(String path) {
        return sysPermissionMapper.selectListByPath(path);
    }
}
