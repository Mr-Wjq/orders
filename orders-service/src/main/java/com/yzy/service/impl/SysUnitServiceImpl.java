package com.yzy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzy.dao.DataDiscountUnitMapper;
import com.yzy.dao.DataProductPriceMapper;
import com.yzy.dao.DataUnitFactoryMapper;
import com.yzy.dao.SysUnitMapper;
import com.yzy.dao.SysUnitUserMapper;
import com.yzy.dao.SysUserMapper;
import com.yzy.entity.DataDiscount;
import com.yzy.entity.DataDiscountUnit;
import com.yzy.entity.DataProductPrice;
import com.yzy.entity.DataUnitFactory;
import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUnit;
import com.yzy.entity.SysUnitUser;
import com.yzy.entity.SysUser;
import com.yzy.entity.vo.SysUnitVO;
import com.yzy.service.SysUnitService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;
import com.yzy.utils.SystemConstant;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class SysUnitServiceImpl implements SysUnitService {
	private Logger logger = LoggerFactory.getLogger(SysUnitServiceImpl.class);
	@Autowired
	private SysUnitMapper sysUnitMapper;
	@Autowired
	private SysUnitUserMapper sysUnitUserMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private DataUnitFactoryMapper dataUnitFactoryMapper;
	@Autowired
	private DataDiscountUnitMapper dataDiscountUnitMapper;
	
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
			example.createCriteria().andEqualTo("unitName", sysunit.getUnitName()).andNotEqualTo("status",0);
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

	@Override
	public LayuiTable select(int page, int limit, String unitName, Integer unitType, Integer unitProvinceId,
			Integer unitCityId) {
		PageHelper.startPage(page, limit);
		List<SysUnitVO> list = sysUnitMapper.selectSysUnitVO(unitName,unitType,unitProvinceId,unitCityId);
		PageInfo<SysUnitVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}
	@Override
	public List<SysUnitVO> selectAllFactory() {
		return sysUnitMapper.selectSysUnitVO(null,4,null,null);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result update(SysUnit sysunit) {
		try {
			
			Example example = new Example(SysUnit.class);
			example.createCriteria()
			.andEqualTo("unitName", sysunit.getUnitName())
			.andNotEqualTo("id", sysunit.getId())
			.andNotEqualTo("status",0);
			List<SysUnit> list = sysUnitMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("单位名称已被创建!");
			}
			if(sysunit.getDataUnitFromId() == null) {
				sysunit.setDataUnitFromId((long)0);
			}
			sysunit.setUpdateTime(new Date());
			sysUnitMapper.updateByPrimaryKeySelective(sysunit);
		} catch (Exception e) {
			logger.error("修改单位出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result delete(String ids) {
		String[] split = ids.split(",");
		try {
			for (String id : split) {
				SysUnit sysUnit = new SysUnit();
				sysUnit.setId(Long.parseLong(id));
				sysUnit.setStatus(0);
				sysUnitMapper.updateByPrimaryKeySelective(sysUnit);
				
				Example example = new Example(SysUnitUser.class);
				example.createCriteria().andEqualTo("sysUnitId", id);
				List<SysUnitUser> list = sysUnitUserMapper.selectByExample(example);
				for (SysUnitUser sysUnitUser : list) {
					SysUser sysUser = new SysUser();
					sysUser.setId(sysUnitUser.getSysUserId());
					sysUser.setStatus(0);
					sysUserMapper.updateByPrimaryKeySelective(sysUser);
				}
				
				SysUnitUser sysUnitUser = new SysUnitUser();
				sysUnitUser.setStatus(0);
				sysUnitUserMapper.updateByExampleSelective(sysUnitUser, example);
				
				Example example2 = new Example(DataDiscountUnit.class);
				example2.createCriteria().andEqualTo("sysUnitId", id);
				DataDiscountUnit dataDiscountUnit = new DataDiscountUnit();
				dataDiscountUnit.setStatus(0);
				dataDiscountUnitMapper.updateByExampleSelective(dataDiscountUnit, example2);
				
				Example example3 = new Example(DataUnitFactory.class);
				example3.createCriteria().andEqualTo("unitId", id);
				dataUnitFactoryMapper.deleteByExample(example3);
			}
		} catch (NumberFormatException e) {
			logger.error("删除单位失败", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}

	@Override
	public List<SysUnit> selectMyFactory(Long unitId) {
		List<SysUnit> list = dataUnitFactoryMapper.selectMyFactory(unitId);
		return list;
	}
}
