package com.yzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.DataDiscount;
import com.yzy.entity.vo.DataDiscountVO;
import com.yzy.utils.MyMapper;

public interface DataDiscountMapper extends MyMapper<DataDiscount> {

	List<DataDiscountVO> selectVo(@Param("discountName")String discountName, 
								  @Param("discountType")Integer discountType);
}