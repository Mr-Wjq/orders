package com.yzy.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.SysUnitUser;
import com.yzy.entity.vo.UserUnitVO;
import com.yzy.utils.MyMapper;

public interface SysUnitUserMapper extends MyMapper<SysUnitUser> {

	SysUnitUser selectUnitUserByUserId(@Param("userId")Long userId);

	List<UserUnitVO> selectUserAndUnit(@Param("unitIdList")List<Long> unitIdList,
									   @Param("zhName")String zhName,
									   @Param("unitName")String unitName,
									   @Param("loginName")String loginName,
									   @Param("phone")String phone,
									   @Param("roleIdList")ArrayList<Integer> roleIdList,
									   @Param("status")Integer status);
}