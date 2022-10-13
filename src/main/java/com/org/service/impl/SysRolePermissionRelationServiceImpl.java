package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.mapper.SysRolePermissionRelationMapper;
import com.org.pojo.SysRolePermissionRelation;
import com.org.service.SysRolePermissionRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-权限关联关系表 服务实现类
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
@Service
public class SysRolePermissionRelationServiceImpl extends ServiceImpl<SysRolePermissionRelationMapper, SysRolePermissionRelation> implements SysRolePermissionRelationService {

}
