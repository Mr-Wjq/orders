package com.yzy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUnit;
import com.yzy.entity.vo.SysUnitVO;
import com.yzy.service.SysUnitService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

@Controller
@RequestMapping("unit")
public class UnitController extends BaseController{

	@Autowired
	private SysUnitService sysUnitService;
	
	@RequestMapping("toSysUnit")
	public String toSysUnit() {
		return "user/sysUnit";
	}
	
	@RequestMapping("select")
	@ResponseBody
	public LayuiTable select(int page,int limit,String unitName,Integer unitType,Integer unitProvinceId,Integer unitCityId) {
		return sysUnitService.select(page,limit, unitName, unitType, unitProvinceId, unitCityId);
	}
	@RequestMapping("selectAllFactory")
	@ResponseBody
	public List<SysUnitVO> selectAllFactory() {
		return sysUnitService.selectAllFactory();
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Result insert(SysUnit sysunit) {
		Result result = sysUnitService.insert(sysunit);
		return result;
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(SysUnit sysunit) {
		if(sysunit.getId() == null) {
			return Result.error("缺少参数");
		}
		Result result = sysUnitService.update(sysunit);
		return result;
	}
	@PostMapping("delete")
	@ResponseBody
	public Result delete(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("缺少参数");
		}
		return sysUnitService.delete(ids);
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
	public HashMap<String,Object> selectUserFactory(Long unitId) {
		if(unitId == null) {
			Subject subject = SecurityUtils.getSubject();
			LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
			unitId = loginInfo.getUnitId();
		}
		return sysUnitService.selectUserFactory(unitId);
	}
	@RequestMapping(value = "updateUserFactory", method = RequestMethod.POST)
	@ResponseBody
	public Result updateUserFactory(String type,String factoryId,Long unitId) {
		if(unitId == null) {
			Subject subject = SecurityUtils.getSubject();
			LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
			unitId = loginInfo.getUnitId();
		}
		return sysUnitService.updateUserFactory( type, factoryId,unitId);
	}
	@RequestMapping(value = "selectFactoryByProductId", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUnit> selectFactoryByProductId(Long productId) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return sysUnitService.selectFactoryByProductId(productId,loginInfo.getUnitId());
	}
	
	/**
	 * 获取和我合作的工厂
	 */
	@RequestMapping(value = "selectMyFactory", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUnit> selectMyFactory() {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return sysUnitService.selectMyFactory(loginInfo.getUnitId());
	}
}
