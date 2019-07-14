package com.yzy.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.entity.DataKousaoyi;
import com.yzy.entity.DataUnitFrom;
import com.yzy.service.DataUnitFromService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

@Controller
@RequestMapping("unitFrom")
public class DataUnitFromController extends BaseController{

	@Autowired
	DataUnitFromService dataUnitFromService;
	
	@RequestMapping("toUnitFrom")
	public String toUnitFrom() {
		return "baseData/unitFrom";
	}
	
	@RequestMapping("select")
	@ResponseBody
	public LayuiTable select(int page,int limit) {
		return dataUnitFromService.select(page,limit);
	}
	@PostMapping("insert")
	@ResponseBody
	public Result insert(DataUnitFrom dataUnitFrom) {
		return dataUnitFromService.insert(dataUnitFrom);
	}
	@PostMapping("update")
	@ResponseBody
	public Result update(DataUnitFrom dataUnitFrom) {
		if(dataUnitFrom.getId() == null) {
			return Result.error("缺少参数");
		}
		return dataUnitFromService.update(dataUnitFrom);
	}
	@PostMapping("delete")
	@ResponseBody
	public Result delete(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("缺少参数");
		}
		return dataUnitFromService.delete(ids);
	}
	
}
