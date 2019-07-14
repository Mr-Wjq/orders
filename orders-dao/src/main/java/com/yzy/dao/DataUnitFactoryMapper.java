package com.yzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.DataUnitFactory;
import com.yzy.entity.SysUnit;
import com.yzy.utils.MyMapper;

public interface DataUnitFactoryMapper extends MyMapper<DataUnitFactory> {

	List<SysUnit> selectMyFactory(@Param("unitId")Long unitId);
}