package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.mapper.SysUserMapper;
import com.org.pojo.SysUser;
import com.org.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser selectByName(String userName) {
        return sysUserMapper.selectByName(userName);
    }
}
