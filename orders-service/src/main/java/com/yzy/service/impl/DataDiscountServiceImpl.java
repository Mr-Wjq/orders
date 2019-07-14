package com.yzy.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzy.dao.DataDiscountMapper;
import com.yzy.dao.DataDiscountUnitMapper;
import com.yzy.entity.DataDiscount;
import com.yzy.entity.DataDiscountUnit;
import com.yzy.entity.vo.DataDiscountUnitVO;
import com.yzy.entity.vo.DataDiscountVO;
import com.yzy.service.DataDiscountService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;
import com.yzy.utils.SystemConstant;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DataDiscountServiceImpl implements DataDiscountService {
	private static Logger logger = LoggerFactory.getLogger(DataDiscountServiceImpl.class);

	@Autowired
	DataDiscountMapper dataDiscountMapper; 
	
	@Autowired
	DataDiscountUnitMapper dataDiscountUnitMapper;
	
	@Override
	public LayuiTable select(int page, int limit, String discountName, Integer discountType) {
		
		PageHelper.startPage(page, limit);
		List<DataDiscountVO> list = dataDiscountMapper.selectVo(discountName,discountType);
		PageInfo<DataDiscountVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result insert(DataDiscount dataDiscount) {
		Example example = new Example(DataDiscount.class);
		example.createCriteria()
		.andEqualTo("discountName", dataDiscount.getDiscountName())
		.andEqualTo("discountType",dataDiscount.getDiscountType())
		.andNotEqualTo("status", 0);
		List<DataDiscount> list = dataDiscountMapper.selectByExample(example);
		if(!list.isEmpty()) {
			return Result.error(dataDiscount.getDiscountName()+" 已被使用!请检查是否有相同类型的优惠券");
		}
		try {
			dataDiscount.setCreateTime(new Date());
			dataDiscountMapper.insertSelective(dataDiscount);
		} catch (Exception e) {
			logger.error("添加优惠券失败", e);
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result update(DataDiscount dataDiscount) {
		Example example = new Example(DataDiscount.class);
		example.createCriteria()
		.andEqualTo("discountName", dataDiscount.getDiscountName())
		.andEqualTo("discountType",dataDiscount.getDiscountType())
		.andNotEqualTo("id",dataDiscount.getId())
		.andNotEqualTo("status", 0);
		List<DataDiscount> list = dataDiscountMapper.selectByExample(example);
		if(!list.isEmpty()) {
			return Result.error(dataDiscount.getDiscountName()+" 已被使用!请检查是否有相同类型的优惠券");
		}
		try {
			dataDiscount.setStatus(1);
			dataDiscountMapper.updateByPrimaryKey(dataDiscount);
		} catch (Exception e) {
			logger.error("修改优惠券失败", e);
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
				DataDiscount dataDiscount = new DataDiscount();
				dataDiscount.setId(Long.parseLong(id));
				dataDiscount.setStatus(0);
				dataDiscountMapper.updateByPrimaryKeySelective(dataDiscount);
				
				Example example = new Example(DataDiscountUnit.class);
				example.createCriteria().andEqualTo("dataDiscountId", id);
				DataDiscountUnit dataDiscountUnit = new DataDiscountUnit();
				dataDiscountUnit.setStatus(0);
				dataDiscountUnitMapper.updateByExampleSelective(dataDiscountUnit, example);
			}
		} catch (NumberFormatException e) {
			logger.error("删除优惠券失败", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}

	@Override
	public LayuiTable selectUnitDiscount(int page, int limit, Long sysUnitId) {
		PageHelper.startPage(page, limit);
		List<DataDiscountUnitVO> list = dataDiscountUnitMapper.selectDiscountByUnitId(sysUnitId);
		PageInfo<DataDiscountUnitVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}

	@Override
	public List<DataDiscountVO> selectDiscountAll() {
		List<DataDiscountVO> list = dataDiscountMapper.selectVo(null,null);
		return list;
	}

	@Override
	public Result insertDscUnit(DataDiscountUnit dataDiscountUnit) {
		try {
			
			Example example = new Example(DataDiscountUnit.class);
			example.createCriteria()
			.andEqualTo("dataDiscountId", dataDiscountUnit.getDataDiscountId())
			.andEqualTo("sysUnitId", dataDiscountUnit.getSysUnitId())
			.andNotEqualTo("status", 0);
			List<DataDiscountUnit> list = dataDiscountUnitMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("该优惠券已在列表中,请勿重复添加!");
			}
			dataDiscountUnit.setCreateTime(new Date());
			dataDiscountUnitMapper.insertSelective(dataDiscountUnit);
		} catch (Exception e) {
			logger.error("给单位设置优惠券错误", e);
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}

	@Override
	public Result updateDscUnit(DataDiscountUnit dataDiscountUnit) {
		try {
			dataDiscountUnitMapper.updateByPrimaryKeySelective(dataDiscountUnit);
		} catch (Exception e) {
			logger.error("修改单位优惠券错误", e);
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}

	@Override
	public Result deleteDscUnit(String ids) {
		String[] split = ids.split(",");
		try {
			for (String id : split) {
				DataDiscountUnit dataDiscountUnit = new DataDiscountUnit();
				dataDiscountUnit.setId(Long.parseLong(id));
				dataDiscountUnit.setStatus(0);
				dataDiscountUnitMapper.updateByPrimaryKeySelective(dataDiscountUnit);
			}
		} catch (NumberFormatException e) {
			logger.error("删除单位优惠券失败", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}
}
