package com.yzy.dao;


import org.apache.ibatis.annotations.Param;

import com.yzy.entity.SysRolePermission;

import java.util.List;

public interface SysRolePermissionMapper {
    //新增
    public Long insert(SysRolePermission SysRolePermission);

    //更新
    public void update(SysRolePermission SysRolePermission);

    //通过对象进行查询
    public SysRolePermission select(SysRolePermission SysRolePermission);

    //通过id进行查询
    public SysRolePermission selectById(@Param("id") Long id);

    //查询全部
    public List<SysRolePermission> selectAll();

    //查询数量
    public int selectCounts();

    void deleteByRoleId(@Param("roleId") Long roleId);

    List<SysRolePermission> selectByRoleId(@Param("roleId") Long roleId);

    //根据角色权限中间表查出 权限pid为0  的数据
	public List<SysRolePermission> selectPidByRoleIdAndPid(@Param("roleId") Long roleId, @Param("sysPermissionPid") Long sysPermissionPid);

	public void deleteByPermissionId(@Param("permissionId") Long permissionId);
}