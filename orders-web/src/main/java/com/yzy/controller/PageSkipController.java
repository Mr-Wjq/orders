package com.yzy.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yzy.dao.SysUnitMapper;
import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUnit;
import com.yzy.service.SysUnitService;

@Controller
@RequestMapping("page")
public class PageSkipController {

	@Autowired
	private SysUnitMapper sysUnitMapper;
	
	@RequestMapping("index")
	public String index() {
		return "system/index";
	}
	
	@RequestMapping(value = "toMain", method = RequestMethod.GET)
    public String toMain() {
    	Subject subject = SecurityUtils.getSubject();
    	LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");
    	return loginInfo.getFirstPage();
    }
	
	@RequestMapping(value = "role", method = RequestMethod.GET)
	public String role() {
		return "system/role";
	}
	
	@RequestMapping("systemUser")
	public String systemUser() {
		return "user/systemUser";
	}
	@RequestMapping("doctorUser")
	public String doctorUser() {
		return "user/doctorUser";
	}
	@RequestMapping("factoryUser")
	public String factoryUser() {
		return "user/factoryUser";
	}
	
	/*
	 * 治疗类型
	 */
	@RequestMapping("toCure")
	public String toCure() {
		return "baseData/cure";
	}
	/*
	 * 产品名称
	 */
	@RequestMapping("toProduct")
	public String toProduct() {
		return "baseData/product";
	}
	/*
	 * 注册
	 */
	@RequestMapping("toRegister")
	public String toRegister() {
		return "system/register";
	}
	/*
	 * 忘记密码
	 */
	@RequestMapping("toForgetPassword")
	public String toForgetPassword() {
		return "system/forgetPassword";
	}

	@RequestMapping("toFactoryProduct")
	public String toFactoryProduct() {
		return "baseData/factoryProduct";
	}
	/*
	 * 工厂的用户管理
	 */
	@RequestMapping("toFactoryUserForFactory")
	public String toFactoryUserForFactory() {
		return "user/factoryUserForFactory";
	}
	/*
	 * 医院、诊所的用户管理
	 */
	@RequestMapping("toDoctorUserForDoctor")
	public String toDoctorUserForDoctor() {
		return "user/doctorUserForDoctor";
	}
	/*
	 * 医院、诊所的订单页面
	 */
	@RequestMapping("toDoctorOrders")
	public String toDoctorOrders() {
		return "orders/doctorOrders";
	}
	/*
	 * 工厂的订单页面
	 */
	@RequestMapping("toFactoryOrders")
	public String toFactoryOrders() {
		return "orders/factoryOrders";
	}

	@RequestMapping("toFactoryProductForDoctor")
	public String toFactoryProductForDoctor() {
		return "baseData/factoryProductForDoctor";
	}
	@RequestMapping("toFactoryProductForSystem")
	public String toFactoryProductForSystem() {
		return "baseData/factoryProductForSystem";
	}
	@RequestMapping("toSystemOrders")
	public String toSystemOrders() {
		return "orders/systemOrders";
	}
	@RequestMapping("toSystemStatistics")
	public String toSystemStatistics() {
		return "statistics/systemStatistics";
	}
	@RequestMapping("toDoctorStatistics")
	public String toDoctorStatistics() {
		return "statistics/doctorStatistics";
	}
	@RequestMapping("toFactoryStatistics")
	public String toFactoryStatistics() {
		return "statistics/factoryStatistics";
	}
	@RequestMapping("systemPerson")
	public String systemPerson() {
		return "user/systemPerson";
	}
	@RequestMapping("doctorPerson")
	public String doctorPerson() {
		return "user/doctorPerson";
	}
	@RequestMapping("factoryPerson")
	public String factoryPerson() {
		return "user/factoryPerson";
	}
	
}
