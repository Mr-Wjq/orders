package com.yzy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yzy.entity.SysUnit;
import com.yzy.entity.vo.SysUnitVO;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

public interface SysUnitService {

	List<SysUnit> getSubjectionUnit();
	Result getUnitListByUnitType(ArrayList<Integer> unitType);
	Result insert(SysUnit sysunit);
	Result unitType(Integer unitType);
	Result selectByUnitName(String unitName);
	SysUnit selectByUnitId(Long unitId);
	HashMap<String,Object> selectUserFactory(Long unitId);
	Result updateUserFactory(String type, String factoryId, Long unitId);
	List<SysUnit> selectFactoryByProductId(Long productId, Long unitId);
	LayuiTable select(int page, int limit, String unitName, Integer unitType, Integer unitProvinceId,
			Integer unitCityId);
	Result update(SysUnit sysunit);
	Result delete(String ids);
	List<SysUnit> selectMyFactory(Long unitId);
	List<SysUnitVO> selectAllFactory();
}
