package com.yzy.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzy.entity.DataOrders;
import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUser;
import com.yzy.entity.vo.OrdersVO;
import com.yzy.service.OrdersService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;
import com.yzy.utils.idworker.Sid;

@Controller
@RequestMapping("orders")
public class OrdersController extends BaseController{

	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping(value = "createOrders", method = RequestMethod.POST)
	@ResponseBody
	public Result createOrders(@RequestParam String patientName,
							   @RequestParam Integer patientAge,
							   @RequestParam Integer patientSex,
							   @RequestParam Integer patientType,
							   @RequestParam String ordersAccessory,
							   MultipartFile accessoryFile,
							   String toothPosition1,
							   String toothPosition2,
							   String toothPosition3,
							   String toothPosition4,
							   String color,
							   @RequestParam Long baseCureId,
							   @RequestParam Long baseProductId,
							   @RequestParam Long receiveUnitId,
							   @RequestParam String remarks) {
		DataOrders dataOrders = new DataOrders();
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		dataOrders.setPatientName(patientName);
		dataOrders.setPatientAge(patientAge);
		dataOrders.setPatientSex(patientSex);
		dataOrders.setPatientType(patientType);
		dataOrders.setOrdersAccessory(ordersAccessory);
		dataOrders.setToothPosition1(toothPosition1);
		dataOrders.setToothPosition2(toothPosition2);
		dataOrders.setToothPosition3(toothPosition3);
		dataOrders.setToothPosition4(toothPosition4);
		dataOrders.setColor(color);
		dataOrders.setBaseCureId(baseCureId);
		dataOrders.setBaseProductId(baseProductId);
		dataOrders.setReceiveUnitId(receiveUnitId);
		dataOrders.setCreateUnitId(loginInfo.getUnitId());
		dataOrders.setCreateUserId(loginInfo.getId());
		dataOrders.setOrdersNum(Sid.nextShort());
		dataOrders.setRemarks(remarks);
		dataOrders.setStatus(2);
		dataOrders.setCreateTime(new Date());
		Result result = ordersService.createOrders(dataOrders,accessoryFile);
		return result;
	}
	
	@RequestMapping(value = "updateOrders", method = RequestMethod.POST)
	@ResponseBody
	public Result updateOrders(@RequestParam Long ordersId,
							   @RequestParam String patientName,
							   @RequestParam Integer patientAge,
							   @RequestParam Integer patientSex,
							   @RequestParam Integer patientType,
							   @RequestParam String ordersAccessory,
							   MultipartFile accessoryFile,
							   String toothPosition1,
							   String toothPosition2,
							   String toothPosition3,
							   String toothPosition4,
							   String color,
							   @RequestParam Long baseCureId,
							   @RequestParam Long baseProductId,
							   @RequestParam Long receiveUnitId,
							   @RequestParam String remarks) {
		DataOrders dataOrders = new DataOrders();
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		dataOrders.setId(ordersId);
		dataOrders.setPatientName(patientName);
		dataOrders.setPatientAge(patientAge);
		dataOrders.setPatientSex(patientSex);
		dataOrders.setPatientType(patientType);
		dataOrders.setOrdersAccessory(ordersAccessory);
		dataOrders.setToothPosition1(toothPosition1);
		dataOrders.setToothPosition2(toothPosition2);
		dataOrders.setToothPosition3(toothPosition3);
		dataOrders.setToothPosition4(toothPosition4);
		dataOrders.setColor(color);
		dataOrders.setBaseCureId(baseCureId);
		dataOrders.setBaseProductId(baseProductId);
		dataOrders.setReceiveUnitId(receiveUnitId);
		dataOrders.setCreateUnitId(loginInfo.getUnitId());
		dataOrders.setCreateUserId(loginInfo.getId());
		dataOrders.setOrdersNum(Sid.nextShort());
		dataOrders.setRemarks(remarks);
		dataOrders.setStatus(2);
		dataOrders.setUpdateTime(new Date());
		Result result = ordersService.updateOrders(dataOrders,accessoryFile);
		return result;
	}
	@RequestMapping(value = "selectDoctorOrders", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectDoctorOrders(int page,int limit,String ordersNum,String patientName,String receiveUnitName,Integer status,String createTime) {
		return ordersService.selectDoctorOrders( page, limit, ordersNum, patientName, receiveUnitName, status, createTime);
	}
	@RequestMapping(value = "selectFactoryOrders", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectFactoryOrders(int page,int limit,String ordersNum,String patientName,String createUnitName,Integer status,String createTime) {
		return ordersService.selectFactoryOrders( page, limit, ordersNum, patientName, createUnitName, status, createTime);
	}
	@RequestMapping(value = "selectSystemOrders", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectSystemOrders(int page,int limit,
			String ordersNum,String patientName,String receiveUnitName,
			String createUnitName,Integer status,String createTime) {
		return ordersService.selectSystemOrders( page, limit, ordersNum, patientName, createUnitName,receiveUnitName, status, createTime);
	}
	@RequestMapping(value = "selectOrdersById", method = RequestMethod.GET)
	@ResponseBody
	public OrdersVO selectOrdersById(Long ordersId) {
		return ordersService.selectOrdersById(ordersId);
	}
	
	@RequestMapping(value = "ordersDetails", method = RequestMethod.GET)
	@ResponseBody
	public Result ordersDetails(Long ordersId) {
		return ordersService.ordersDetails(ordersId);
	}
	
	@RequestMapping(value = "receiveOrders", method = RequestMethod.POST)
	@ResponseBody
	public Result receiveOrders(@RequestParam Long ordersId) {
		DataOrders dataOrders = new DataOrders();
		dataOrders.setId(ordersId);
		dataOrders.setStatus(4);
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		dataOrders.setReceiveUserId(loginInfo.getId());
		dataOrders.setReceiveTime(new Date());
		dataOrders.setUpdateTime(new Date());
		return ordersService.updateOrdersStatus(dataOrders);
	}
	@RequestMapping(value = "beginMade", method = RequestMethod.POST)
	@ResponseBody
	public Result beginMade(@RequestParam Long ordersId) {
		DataOrders dataOrders = new DataOrders();
		dataOrders.setId(ordersId);
		dataOrders.setStatus(5);
		dataOrders.setUpdateTime(new Date());
		return ordersService.updateOrdersStatus(dataOrders);
	}
	@RequestMapping(value = "qianshou", method = RequestMethod.POST)
	@ResponseBody
	public Result qianshou(@RequestParam Long ordersId) {
		DataOrders dataOrders = new DataOrders();
		dataOrders.setId(ordersId);
		dataOrders.setStatus(7);
		dataOrders.setFinishTime(new Date());
		dataOrders.setUpdateTime(new Date());
		return ordersService.updateOrdersStatus(dataOrders);
	}
	@RequestMapping(value = "refuseOrders", method = RequestMethod.POST)
	@ResponseBody
	public Result refuseOrders(@RequestParam Long ordersId,@RequestParam String refuseReason) {
		DataOrders dataOrders = new DataOrders();
		dataOrders.setId(ordersId);
		dataOrders.setStatus(3);
		dataOrders.setRefuseReason(refuseReason);
		dataOrders.setUpdateTime(new Date());
		return ordersService.updateOrdersStatus(dataOrders);
	}

	@RequestMapping(value = "send", method = RequestMethod.POST)
	@ResponseBody
	public Result send(@RequestParam Long ordersId,@RequestParam String expressName,@RequestParam String expressNum) {
		DataOrders dataOrders = new DataOrders();
		dataOrders.setId(ordersId);
		dataOrders.setStatus(6);
		dataOrders.setExpressName(expressName);
		dataOrders.setExpressNum(expressNum);
		dataOrders.setSendTime(new Date());
		dataOrders.setUpdateTime(new Date());
		return ordersService.updateOrdersStatus(dataOrders);
	}
	
	@RequestMapping(value = "downloadAccessory", method = RequestMethod.GET)
	public void downloadAccessory(Long ordersId,HttpServletResponse response) {
		 ordersService.downloadAccessory( ordersId, response);
	}
}
