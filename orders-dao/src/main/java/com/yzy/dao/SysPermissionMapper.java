package com.yzy.dao;


import org.apache.ibatis.annotations.Param;

import com.yzy.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
    //新增
    public Long insert(SysPermission SysPermission);

    //更新
    public void update(SysPermission SysPermission);

    //通过对象进行查询
    public SysPermission select(SysPermission SysPermission);

    //通过id进行查询
    public SysPermission selectById(@Param("id") Long id);

    //查询全部
    public List<SysPermission> selectAll();

    //查询数量
    public int selectCounts();

    boolean isExistName(@Param("name") String name);

    boolean isExistCode( @Param("code") String code);

    boolean isExistNameExcludeId(@Param("id") long id,  @Param("name") String name);

    boolean isExistCodeExcludeId(@Param("id") long id,  @Param("code") String code);
    
    //通过角色ID查权限信息
    public List<SysPermission> selectByRoleId(@Param("roleId") Long roleId);

	public List<SysPermission> selectByPid(@Param("sysPermissionId") Long sysPermissionId);

	public void delete(@Param("id") long id);
}