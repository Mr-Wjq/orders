package com.yzy.service;

import java.util.List;

import com.yzy.entity.DataDiscount;
import com.yzy.entity.DataDiscountUnit;
import com.yzy.entity.vo.DataDiscountVO;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

public interface DataDiscountService {

	LayuiTable select(int page, int limit, String discountName, Integer discountType);

	Result insert(DataDiscount dataDiscount);

	Result update(DataDiscount dataDiscount);

	Result delete(String ids);

	LayuiTable selectUnitDiscount(int page, int limit, Long sysUnitId);

	List<DataDiscountVO> selectDiscountAll();

	Result insertDscUnit(DataDiscountUnit dataDiscountUnit);

	Result updateDscUnit(DataDiscountUnit dataDiscountUnit);

	Result deleteDscUnit(String ids);

}
