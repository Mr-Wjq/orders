package com.yzy.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUnit;
import com.yzy.entity.SysUser;
import com.yzy.service.SysUserService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private SysUserService sysUserService;
	
	/*******************************************系统管理员***************************************************/
	@RequestMapping(value = "selectUserAndUnit", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectUserAndUnit(int page,int limit,String zhName,String unitName,String loginName,String phone,Integer _status) {
		ArrayList<Integer> roleIdList = new ArrayList<Integer>();
		roleIdList.add(1);
		return sysUserService.selectUserAndUnit( page, limit, zhName, unitName, loginName, phone,roleIdList, _status);
	}
	
	@RequestMapping(value = "systemInsert", method = RequestMethod.POST)
	@ResponseBody
	public Result systemInsert(SysUser sysUser) {
		long unitId = 1;
		long roleId = 1;
		Result result = sysUserService.systemInsert(sysUser,unitId,roleId);
		return result;
	}
	@RequestMapping(value = "systemUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Result systemUpdate(SysUser sysUser) {
		if(sysUser.getId() == 1) {
			return Result.error("不可修改"); 
		}
		Result result = sysUserService.systemUpdate(sysUser);
		return result;
	}
	@RequestMapping(value = "systemDelete", method = RequestMethod.POST)
	@ResponseBody
	public Result systemDelete(@RequestParam String ids) {
		Result result = sysUserService.systemDelete(ids);
		return result;
	}
	/*******************************************医院/诊所用户***************************************************/
	@RequestMapping(value = "selectDoctorUserAndUnit", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectDoctorUserAndUnit(int page,int limit,String zhName,String unitName,String loginName,String phone,Integer _status) {
		ArrayList<Integer> roleIdList = new ArrayList<Integer>();
		roleIdList.add(2);
		roleIdList.add(3);
		return sysUserService.selectUserAndUnit( page, limit, zhName, unitName, loginName, phone,roleIdList, _status);
	}
	
	@RequestMapping(value = "doctorInsert", method = RequestMethod.POST)
	@ResponseBody
	public Result doctorInsert(SysUser sysUser,@RequestParam Long roleId,@RequestParam Long unitId) {
		Result result = sysUserService.systemInsert(sysUser,unitId,roleId);
		return result;
	}
	@RequestMapping(value = "doctorUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Result doctorUpdate(SysUser sysUser,
								SysUnit sysUnit,
								@RequestParam Long userId,
								@RequestParam Long roleId,
								@RequestParam Long unitId) {
		sysUser.setId(userId);
		sysUnit.setId(unitId);
		Result result = sysUserService.updateUnitAndUser(sysUser,sysUnit,roleId);
		return result;
	}
	@RequestMapping(value = "selecDoctorUserAndUnit", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selecDoctorUserAndUnit(int page,int limit,String zhName,String unitName,String loginName,String phone,Integer _status) {
		ArrayList<Integer> roleIdList = new ArrayList<Integer>();
		roleIdList.add(2);
		roleIdList.add(3);
		return sysUserService.selectUserAndUnit( page, limit, zhName, unitName, loginName, phone,roleIdList, _status);
	}
	/*******************************************工厂用户***************************************************/
	@RequestMapping(value = "selecFactoryUserAndUnit", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selecFactoryUserAndUnit(int page,int limit,String zhName,String unitName,String loginName,String phone,Integer _status) {
		ArrayList<Integer> roleIdList = new ArrayList<Integer>();
		roleIdList.add(4);
		roleIdList.add(5);
		return sysUserService.selectUserAndUnit( page, limit, zhName, unitName, loginName, phone,roleIdList, _status);
	}
	@RequestMapping(value = "personalUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Result personalUpdate(SysUser sysUser,
									 SysUnit sysUnit) {
		
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		
		sysUser.setId(loginInfo.getId());
		sysUnit.setId(loginInfo.getUnitId());
		return sysUserService.personalUpdate(sysUser,sysUnit);
	}
	
	/***************************************注册************************************************/
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public Result register(SysUser sysUser,SysUnit SysUnit,String phoneCode) {
		
		Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	String sess_phoneCode = (String) session.getAttribute("phoneCode");
    	String sess_phoneNum = (String) session.getAttribute("phoneNum");
		if(StringUtils.isBlank(sess_phoneCode)) {
			return Result.error("手机验证码过期,请重新获取");
		}
		if(!sess_phoneCode.equals(phoneCode)) {
			return Result.error("手机验证码错误");
		}
		if(!sess_phoneNum.equals(sess_phoneNum)) {
			return Result.error("手机号码与验证码不匹配");
		}
		Long roleId = null;
		if(SysUnit.getUnitType() == 4) { //注册的默认为负责人
			roleId = (long) 4;
		}else {
			roleId = (long) 2;
		}
		return sysUserService.register(sysUser,SysUnit,roleId);
	}
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public Result resetPassword(String phone,String password) {
		if(StringUtils.isBlank(password)) {
			return Result.error("密码不能为空");
		}
		if(StringUtils.isBlank(phone)) {
			return Result.error("手机号不能为空");
		}
		
		return sysUserService.resetPassword(phone, password);
	}
	
	@RequestMapping(value = "uploadUserPhoto", method = RequestMethod.POST)
	@ResponseBody
	public Result uploadUserPhoto(Long userId,MultipartFile photoFile) {
		if(photoFile == null || userId == null) {
			return Result.error("缺少参数");
		}
		return sysUserService.uploadUserPhoto(userId,photoFile);
	}
}
