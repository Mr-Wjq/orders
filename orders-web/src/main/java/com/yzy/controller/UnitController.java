package com.yzy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUnit;
import com.yzy.service.SysUnitService;
import com.yzy.utils.Result;

@Controller
@RequestMapping("unit")
public class UnitController extends BaseController{

	@Autowired
	private SysUnitService sysUnitService;
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Result insert(SysUnit sysunit) {
		Result result = sysUnitService.insert(sysunit);
		return result;
	}
	@RequestMapping(value = "getDoctorUnitList", method = RequestMethod.POST)
	@ResponseBody
	public Result getDoctorUnitList() {
		ArrayList<Integer> unitType = new ArrayList<Integer>();
		unitType.add(1);
		unitType.add(2);
		unitType.add(3);
		Result result = sysUnitService.getUnitListByUnitType(unitType);
		return result;
	}
	@RequestMapping(value = "getFactoryUnitList", method = RequestMethod.POST)
	@ResponseBody
	public Result getFactoryUnitList() {
		ArrayList<Integer> unitType = new ArrayList<Integer>();
		unitType.add(4);
		Result result = sysUnitService.getUnitListByUnitType(unitType);
		return result;
	}
	@RequestMapping(value = "getUnitList", method = RequestMethod.GET)
	@ResponseBody
	public Result getUnitList(Integer unitType) {
		if(unitType == null ) {
			return Result.error();
		}
		Result result = sysUnitService.unitType(unitType);
		return result;
	}
	
	@RequestMapping(value = "selectByUnitName", method = RequestMethod.GET)
	@ResponseBody
	public Result selectByUnitName(String unitName) {
		if(StringUtils.isBlank(unitName)) {
			return Result.error();
		}
		Result result = sysUnitService.selectByUnitName(unitName);
		return result;
	}
	@RequestMapping(value = "selectCurrUnit", method = RequestMethod.GET)
	@ResponseBody
	public Result selectCurrUnit() {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		SysUnit sysUnit = sysUnitService.selectByUnitId(loginInfo.getUnitId());
		if(sysUnit==null) {
			return Result.error();
		}
		return Result.success(sysUnit);
	}
	@RequestMapping(value = "selectUserFactory", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> selectUserFactory() {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return sysUnitService.selectUserFactory(loginInfo.getUnitId());
	}
	@RequestMapping(value = "updateUserFactory", method = RequestMethod.POST)
	@ResponseBody
	public Result updateUserFactory(String type,String factoryId) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return sysUnitService.updateUserFactory( type, factoryId,loginInfo.getUnitId());
	}
	@RequestMapping(value = "selectFactoryByProductId", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUnit> selectFactoryByProductId(Long productId) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return sysUnitService.selectFactoryByProductId(productId,loginInfo.getUnitId());
	}
}
