package com.yzy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yzy.dao.DataUnitFactoryMapper;
import com.yzy.dao.SysUnitMapper;
import com.yzy.entity.DataUnitFactory;
import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUnit;
import com.yzy.service.SysUnitService;
import com.yzy.utils.Result;

import tk.mybatis.mapper.entity.Example;
@Service
public class SysUnitServiceImpl implements SysUnitService {
	private Logger logger = LoggerFactory.getLogger(SysUnitServiceImpl.class);
	@Autowired
	private SysUnitMapper sysUnitMapper;
	@Autowired
	private DataUnitFactoryMapper dataUnitFactoryMapper;
	
	public List<SysUnit> getSubjectionUnit() {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		SysUnit currentUnit = sysUnitMapper.selectByPrimaryKey(loginInfo.getUnitId());
		ArrayList<SysUnit> list = new ArrayList<SysUnit>();
		list.add(currentUnit);
		List<SysUnit> list2 = getUnitByPid(list,currentUnit.getId());
		return list2;
	}
	
	public List<SysUnit> getUnitByPid(List<SysUnit> list,Long pid){
		Example example = new Example(SysUnit.class);
		example.createCriteria().andEqualTo("pid", pid).andNotEqualTo("status", 0);
		List<SysUnit> sysUnitList = sysUnitMapper.selectByExample(example);
		list.addAll(sysUnitList);
		for (SysUnit sysUnit : sysUnitList) {
			getUnitByPid(list,sysUnit.getId());
		}
		return list;
	}
	
	@Override
	public Result getUnitListByUnitType(ArrayList<Integer> unitType) {
		List<SysUnit> unitList = getSubjectionUnit();
		//单位ID集合
        List<Long> unitIdList = new ArrayList<>();
        for (SysUnit sysUnit:unitList) {
            unitIdList.add(sysUnit.getId());
        }
		
		Example example = new Example(SysUnit.class);
		example.createCriteria().andIn("unitType", unitType).andNotEqualTo("status",0);
		List<SysUnit> list = sysUnitMapper.selectByExample(example);
		if(list.isEmpty()) {
			return Result.error("暂无单位数据,请先添加单位信息");
		}
		return Result.success(list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result insert(SysUnit sysunit) {
		try {
			
			Example example = new Example(SysUnit.class);
			example.createCriteria().andEqualTo("unitName", sysunit.getUnitName());
			List<SysUnit> list = sysUnitMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("单位名称已被创建!");
			}
			
			sysunit.setPid((long)1);
			sysunit.setCreateTime(new Date());
			sysUnitMapper.insertSelective(sysunit);
		} catch (Exception e) {
			logger.error("添加单位出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}

	@Override
	public Result unitType(Integer unitType) {
		Example example = new Example(SysUnit.class);
		example.createCriteria().andEqualTo("unitType", unitType).andNotEqualTo("status",0);
		List<SysUnit> list = sysUnitMapper.selectByExample(example);
		if(list.isEmpty()) {
			return Result.error("暂无该类型单位");
		}
		return Result.success(list);
	}

	@Override
	public Result selectByUnitName(String unitName) {
		Example example = new Example(SysUnit.class);
		example.createCriteria().andEqualTo("unitName", unitName);
		List<SysUnit> list = sysUnitMapper.selectByExample(example);
		if(!list.isEmpty()) {
			return Result.error("单位名称已被创建!");
		}
		return Result.success();
	}

	@Override
	public SysUnit selectByUnitId(Long unitId) {
		SysUnit currentUnit = sysUnitMapper.selectByPrimaryKey(unitId);
		return currentUnit;
	}

	@Override
	public HashMap<String,Object> selectUserFactory(Long unitId) {
		List<SysUnit> allFactory = sysUnitMapper.selectAllFactory(unitId);
		List<SysUnit> useFactory = sysUnitMapper.selectUseFactory(unitId);
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("allFactory", allFactory);
		hashMap.put("useFactory", useFactory);
		return hashMap;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateUserFactory(String type, String factoryId, Long unitId) {
		try {
			DataUnitFactory factory = new DataUnitFactory();
			factory.setUnitId(unitId);
			String[] split = factoryId.split(",");
			for (String id : split) {
				factory.setFactoryId(Long.parseLong(id));
				if("insert".equals(type)) {
					dataUnitFactoryMapper.insertSelective(factory);
				}else if("delete".equals(type)) {
					dataUnitFactoryMapper.delete(factory);
				}
			}
		} catch (NumberFormatException e) {
			logger.error("单位常用工厂"+type+"失败",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}

	@Override
	public List<SysUnit> selectFactoryByProductId(Long productId, Long unitId) {
		List<SysUnit> list = new ArrayList<SysUnit>();
		list = sysUnitMapper.selectFactoryByProductIdAndUnitId( productId,  unitId);
		if(list.isEmpty()) {
			list = sysUnitMapper.selectFactoryByProductId(productId);
		}
		return list;
	}
	
}
