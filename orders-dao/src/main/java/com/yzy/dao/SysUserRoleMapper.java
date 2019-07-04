package com.yzy.dao;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper {

	void insert(SysUserRole sysUserRole);

	
	SysUserRole selectByUserId(@Param("userId") long userId);

	List<Long> selectByRoleId(@Param("roleId") long roleId);

    void update(SysUserRole sysUserRole);

    void deleteByUserId(@Param("userId") long userId);
	int deleteByRoleId(@Param("roleId")Long roleId);
}
