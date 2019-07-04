package com.yzy.dao;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.SysFirstPage;
import com.yzy.utils.MyMapper;

public interface SysFirstPageMapper extends MyMapper<SysFirstPage> {

	SysFirstPage selectByRoleId(@Param("sysRoleId")Long sysRoleId);
}