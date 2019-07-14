package com.yzy.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.entity.DataDiscount;
import com.yzy.entity.DataDiscountUnit;
import com.yzy.entity.DataKousaoyi;
import com.yzy.entity.DataUnitFrom;
import com.yzy.entity.vo.DataDiscountVO;
import com.yzy.service.DataDiscountService;
import com.yzy.service.DataUnitFromService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

@Controller
@RequestMapping("discount")
public class DataDiscountController extends BaseController{

	@Autowired
	DataDiscountService dataDiscountService;
	
	@RequestMapping("toDiscount")
	public String toDiscount() {
		return "baseData/discount";
	}
	@RequiresPermissions("system:discount:select")
	@RequestMapping("select")
	@ResponseBody
	public LayuiTable select(int page,int limit,String discountName,Integer discountType) {
		return dataDiscountService.select(page,limit,discountName,discountType);
	}
	
	@RequiresPermissions("system:discount:insert")
	@PostMapping("insert")
	@ResponseBody
	public Result insert(DataDiscount dataDiscount) {
		return dataDiscountService.insert(dataDiscount);
	}
	
	@RequiresPermissions("system:discount:update")
	@PostMapping("update")
	@ResponseBody
	public Result update(DataDiscount dataDiscount) {
		if(dataDiscount.getId() == null) {
			return Result.error("缺少参数");
		}
		return dataDiscountService.update(dataDiscount);
	}
	
	@RequiresPermissions("system:discount:update")
	@PostMapping("delete")
	@ResponseBody
	public Result delete(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("缺少参数");
		}
		return dataDiscountService.delete(ids);
	}
	
	@RequestMapping("selectUnitDiscount")
	@ResponseBody
	public LayuiTable selectUnitDiscount(int page,int limit,Long sysUnitId) {
		return dataDiscountService.selectUnitDiscount(page,limit,sysUnitId);
	}
	@RequestMapping("selectDiscountAll")
	@ResponseBody
	public List<DataDiscountVO> selectDiscountAll() {
		return dataDiscountService.selectDiscountAll();
	}
	
	@RequiresPermissions("system:discount:insert")
	@PostMapping("insertDscUnit")
	@ResponseBody
	public Result insertDscUnit(DataDiscountUnit dataDiscountUnit) {
		return dataDiscountService.insertDscUnit(dataDiscountUnit);
	}
	@RequiresPermissions("system:discount:update")
	@PostMapping("updateDscUnit")
	@ResponseBody
	public Result updateDscUnit(DataDiscountUnit dataDiscountUnit) {
		if(dataDiscountUnit.getId() == null) {
			return Result.error("缺少参数");
		}
		return dataDiscountService.updateDscUnit(dataDiscountUnit);
	}
	@RequiresPermissions("system:discount:update")
	@PostMapping("deleteDscUnit")
	@ResponseBody
	public Result deleteDscUnit(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("缺少参数");
		}
		return dataDiscountService.deleteDscUnit(ids);
	}
}
