package com.yzy.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.entity.DataDiscount;
import com.yzy.entity.DataPatient;
import com.yzy.entity.LoginInfo;
import com.yzy.service.DataPatientService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

@Controller
@RequestMapping("patient")
public class DataPatientController {

	@Autowired
	private DataPatientService dataPatientService;
	
	/*
	 * 跳转到患者页面
	 */
	@RequestMapping("toPatient")
	public String toPatient() {
		return "orders/patient";
	}

	@RequestMapping("select")
	@ResponseBody
	public LayuiTable select(int page,int limit,String patientName,String patientPhone) {
		return dataPatientService.select(page,limit,patientName,patientPhone);
	}
	
	@PostMapping("insert")
	@ResponseBody
	public Result insert(DataPatient dataPatient) {
		return dataPatientService.insert(dataPatient);
	}
	
	@PostMapping("update")
	@ResponseBody
	public Result update(DataPatient dataPatient) {
		if(dataPatient.getId() == null) {
			return Result.error("缺少参数");
		}
		return dataPatientService.update(dataPatient);
	}
	
	@PostMapping("delete")
	@ResponseBody
	public Result delete(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("缺少参数");
		}
		return dataPatientService.delete(ids);
	}

	@GetMapping("selectPatientByCurrUnitId")
	@ResponseBody
	public List<DataPatient> selectPatientByCurrUnitId() {
		Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		return dataPatientService.selectPatientByCurrUnitId(loginInfo.getUnitId());
	}
}
