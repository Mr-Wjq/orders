package com.yzy.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.entity.BaseCure;
import com.yzy.entity.BaseExpress;
import com.yzy.entity.BaseProduct;
import com.yzy.entity.LoginInfo;
import com.yzy.entity.vo.ProductVO;
import com.yzy.service.BaseDataService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

@Controller
@RequestMapping("baseData")
public class BaseDataController extends BaseController{

	@Autowired
	private BaseDataService baseDataService;
	
	@RequestMapping(value = "selectCure", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectCure(int page,int limit) {
		return baseDataService.selectCure( page, limit);
	}
	
	@RequestMapping(value = "selectAllCureList", method = RequestMethod.GET)
	@ResponseBody
	public List<BaseCure> selectAllCureList() {
		return baseDataService.selectAllCureList();
	}

	@RequestMapping(value = "insertCure", method = RequestMethod.POST)
	@ResponseBody
	public Result insertCure(BaseCure baseCure) {
		return baseDataService.insertCure(baseCure);
	}
	@RequestMapping(value = "deleteCure", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteCure(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("请选择要删除的数据");
		}
		return baseDataService.deleteCure(ids);
	}
	@RequestMapping(value = "updateCure", method = RequestMethod.POST)
	@ResponseBody
	public Result updateCure(BaseCure baseCure) {
		return baseDataService.updateCure(baseCure);
	}
	
	/**************************************产品*******************************/
	@RequestMapping(value = "selectProduct", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectProduct(int page,int limit,Long cureId,String productName,String textureName,String brandName) {
		return baseDataService.selectProduct( page, limit, cureId, productName, textureName, brandName);
	}
	@RequestMapping(value = "selectAllProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> selectAllProduct() {
		return baseDataService.selectAllProduct();
	}
	@RequestMapping(value = "insertProduct", method = RequestMethod.POST)
	@ResponseBody
	public Result insertProduct(BaseProduct baseProduct) {
		return baseDataService.insertProduct(baseProduct);
	}
	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
	@ResponseBody
	public Result updateProduct(BaseProduct baseProduct) {
		return baseDataService.updateProduct(baseProduct);
	}
	@RequestMapping(value = "deleteProduct", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteProduct(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("请选择要删除的数据");
		}
		return baseDataService.deleteProduct(ids);
	}
	@RequestMapping(value = "selectFactoryProduct", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectFactoryProduct(int page,int limit,Long cureId,String productName,String textureName,String brandName,String factoryName) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return baseDataService.selectFactoryProduct( page, limit, cureId, productName, textureName, brandName,factoryName,loginInfo.getUnitId());
	}
	@RequestMapping(value = "selectFactoryProductForSystem", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectFactoryProductForSystem(int page,int limit,Long cureId,String productName,String textureName,String brandName,String factoryName) {
		return baseDataService.selectFactoryProduct( page, limit, cureId, productName, textureName, brandName,factoryName,null);
	}
	@RequestMapping(value = "selectFactoryProductForDoctor", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectFactoryProductForDoctor(int page,int limit,Long cureId,String productName,String textureName,String brandName,String factoryName) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return baseDataService.selectFactoryProductForDoctor( page, limit, cureId, productName, textureName, brandName,factoryName,loginInfo.getUnitId());
	}
	@RequestMapping(value = "selectProductByCureId", method = RequestMethod.GET)
	@ResponseBody
	public Result selectProductByCureId(@RequestParam Long cureId) {
		return baseDataService.selectProductByCureId(cureId);
	}
	@RequestMapping(value = "selectNotAddProductVO", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectNotAddProductVO(int page,int limit,Long cureId,String productName,String textureName,String brandName) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return baseDataService.selectNotAddProductVO( page, limit, cureId, productName, textureName, brandName,loginInfo.getUnitId());
	}
	@RequestMapping(value = "factoryAddProduct", method = RequestMethod.POST)
	@ResponseBody
	public Result factoryAddProduct(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("请选择要添加的产品");
		}
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return baseDataService.factoryAddProduct(ids,loginInfo.getUnitId());
	}
	@RequestMapping(value = "factoryDeleteProduct", method = RequestMethod.POST)
	@ResponseBody
	public Result factoryDeleteProduct(String ids) {
		if(StringUtils.isBlank(ids)) {
			return Result.error("请选择要删除的产品");
		}
		return baseDataService.factoryDeleteProduct(ids);
	}
	@RequestMapping(value = "factoryUpdateProductPrice", method = RequestMethod.POST)
	@ResponseBody
	public Result factoryUpdateProductPrice(Long id,BigDecimal price) {
		if(price == null) {
			return Result.error("请输入价格");
		}
		if(id == null) {
			return Result.error("请选择要修改产品");
		}
		return baseDataService.factoryUpdateProductPrice(id,price);
	}
	@RequestMapping(value = "selectOrdersProductVO", method = RequestMethod.GET)
	@ResponseBody
	public LayuiTable selectOrdersProductVO(Long baseCureId) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		return baseDataService.selectOrdersProductVO(baseCureId,loginInfo.getUnitId());
	}
	
	/************************************快递******************************************/
	@RequestMapping(value = "selectBaseExpress", method = RequestMethod.GET)
	@ResponseBody
	public List<BaseExpress> selectBaseExpress() {
		return baseDataService.selectBaseExpress();
	}
}
