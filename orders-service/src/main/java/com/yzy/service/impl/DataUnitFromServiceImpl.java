package com.yzy.service.impl;

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
import com.yzy.dao.DataUnitFromMapper;
import com.yzy.entity.DataKousaoyi;
import com.yzy.entity.DataUnitFrom;
import com.yzy.service.DataUnitFromService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;
import com.yzy.utils.SystemConstant;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service
public class DataUnitFromServiceImpl implements DataUnitFromService{
	private static Logger logger = LoggerFactory.getLogger(DataUnitFromServiceImpl.class);
	@Autowired
	DataUnitFromMapper dataUnitFromMapper;

	@Override
	public LayuiTable select(int page, int limit) {
		Example example = new Example(DataUnitFrom.class);
		example.createCriteria().andNotEqualTo("status", 0);
		PageHelper.startPage(page, limit);
		List<DataUnitFrom> list = dataUnitFromMapper.selectByExample(example);
		PageInfo<DataUnitFrom> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result insert(DataUnitFrom dataUnitFrom) {
		Example example = new Example(DataUnitFrom.class);
		example.createCriteria().andEqualTo("fromName", dataUnitFrom.getFromName()).andNotEqualTo("status", 0);
		List<DataUnitFrom> list = dataUnitFromMapper.selectByExample(example);
		if(!list.isEmpty()) {
			return Result.error(dataUnitFrom.getFromName()+" 已被使用!");
		}
		try {
			dataUnitFromMapper.insertSelective(dataUnitFrom);
		} catch (Exception e) {
			logger.error("添加单位来源失败", e);
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result update(DataUnitFrom dataUnitFrom) {
		Example example = new Example(DataUnitFrom.class);
		example.createCriteria().andEqualTo("fromName", dataUnitFrom.getFromName())
		.andNotEqualTo("id", dataUnitFrom.getId())
		.andNotEqualTo("status", 0);
		List<DataUnitFrom> list = dataUnitFromMapper.selectByExample(example);
		if(!list.isEmpty()) {
			return Result.error(dataUnitFrom.getFromName()+" 已被使用!");
		}
		try {
			dataUnitFromMapper.updateByPrimaryKeySelective(dataUnitFrom);
		} catch (Exception e) {
			logger.error("修改单位来源失败", e);
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
				DataUnitFrom dataUnitFrom = new DataUnitFrom();
				dataUnitFrom.setId(Long.parseLong(id));
				dataUnitFrom.setStatus(0);
				dataUnitFromMapper.updateByPrimaryKeySelective(dataUnitFrom);
			}
		} catch (NumberFormatException e) {
			logger.error("删除单位来源失败", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}
	
	
}
