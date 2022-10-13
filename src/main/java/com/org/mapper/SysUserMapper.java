package com.org.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wuJia
 * @since 2022-10-13
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectByName(String userName);

}
