package com.org.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    public List<SysPermission> selectListByUser(Integer userId);

    /**
     * 查询具体某个接口的权限
     */
    List<SysPermission> selectListByPath(String path);

}
