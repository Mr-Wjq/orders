package com.yzy.dao;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.SysUser;
import com.yzy.utils.MyMapper;

import java.util.List;

public interface SysUserMapper extends MyMapper<SysUser>{

    SysUser selectUserByLoginName(@Param("loginName") String loginName);

	SysUser selectUserByPhone(@Param("phone")String phone);


}