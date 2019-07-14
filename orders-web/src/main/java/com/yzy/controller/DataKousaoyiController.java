package com.yzy.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.entity.DataKousaoyi;
import com.yzy.service.DataKousaoyiService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

@Controller
@RequestMapping("kousaoyi")
public class DataKousaoyiController extends BaseController{

	@Autowired
	DataKousaoyiService dataKousaoyiService;
	
	@RequestMapping("toKousaoyi")
	public String toKousaoyi() {
		return "baseData/kousaoyi";
	}
	
	@RequestMapping("select")
	@ResponseBody
	public LayuiTable select(int page,int limit,String kousaoyiName) {
		return dataKousaoyiService.select(page,limit,kousaoyiName);
	}
	@PostMapping("insert")
	@ResponseBody
	public Result insert(DataKousaoyi dataKousaoyi) {
		return dataKousaoyiService.insert(dataKousaoyi);
	}
	@PostMapping("update")
	@ResponseBody
	public Result update(DataKousaoyi dataKousaoyi) {
		if(dataKousaoyi.getId() == null) {
			return Result.error("缺少参数");
		}
		return dataKousaoyiService.update(dataKousaoyi);
	}
	@PostMapping("delete")
	@ResponseBody
	public Result delete(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("缺少参数");
		}
		return dataKousaoyiService.delete(ids);
	}
}
