package com.yzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.SysUnit;
import com.yzy.utils.MyMapper;

public interface SysUnitMapper extends MyMapper<SysUnit> {

	List<SysUnit> selectUseFactory(@Param("unitId")Long unitId);
	List<SysUnit> selectAllFactory(@Param("unitId")Long unitId);
	List<SysUnit> selectFactoryByProductIdAndUnitId(@Param("productId")Long productId, 
										   @Param("unitId")Long unitId);
	List<SysUnit> selectFactoryByProductId(@Param("productId")Long productId);
}