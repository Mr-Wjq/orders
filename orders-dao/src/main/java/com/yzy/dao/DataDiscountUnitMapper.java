package com.yzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.DataDiscountUnit;
import com.yzy.entity.vo.DataDiscountUnitVO;
import com.yzy.utils.MyMapper;

public interface DataDiscountUnitMapper extends MyMapper<DataDiscountUnit> {

	List<DataDiscountUnitVO> selectDiscountByUnitId(@Param("sysUnitId")Long sysUnitId);
}