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
import com.yzy.dao.DataKousaoyiMapper;
import com.yzy.entity.DataKousaoyi;
import com.yzy.service.DataKousaoyiService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;
import com.yzy.utils.SystemConstant;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DataKousaoyiServiceImpl implements DataKousaoyiService {
	private static Logger logger = LoggerFactory.getLogger(DataKousaoyiServiceImpl.class);
	@Autowired
	DataKousaoyiMapper 	dataKousaoyiMapper;

	@Override
	public LayuiTable select(int page, int limit, String kousaoyiName) {
		PageHelper.startPage(page, limit);
		Example example = new Example(DataKousaoyi.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(kousaoyiName)) {
			criteria.andLike("kousaoyiName", "%"+kousaoyiName+"%");
		}
		criteria.andNotEqualTo("status", 0);
		
		PageHelper.startPage(page, limit);
		List<DataKousaoyi> list = dataKousaoyiMapper.selectByExample(example);
		PageInfo<DataKousaoyi> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}

	@Override
	public Result insert(DataKousaoyi dataKousaoyi) {
		Example example = new Example(DataKousaoyi.class);
		example.createCriteria().andEqualTo("kousaoyiName", dataKousaoyi.getKousaoyiName()).andNotEqualTo("status", 0);
		List<DataKousaoyi> list = dataKousaoyiMapper.selectByExample(example);
		if(!list.isEmpty()) {
			return Result.error(dataKousaoyi.getKousaoyiName()+" 已被使用!");
		}
		try {
			dataKousaoyiMapper.insertSelective(dataKousaoyi);
		} catch (Exception e) {
			logger.error("添加口扫仪失败", e);
			Result.error();
		}
		return Result.success();
	}

	@Override
	public Result update(DataKousaoyi dataKousaoyi) {
		Example example = new Example(DataKousaoyi.class);
		example.createCriteria().andEqualTo("kousaoyiName", dataKousaoyi.getKousaoyiName())
		.andNotEqualTo("id", dataKousaoyi.getId())
		.andNotEqualTo("status", 0);
		List<DataKousaoyi> list = dataKousaoyiMapper.selectByExample(example);
		if(!list.isEmpty()) {
			return Result.error(dataKousaoyi.getKousaoyiName()+" 已被使用!");
		}
		try {
			dataKousaoyiMapper.updateByPrimaryKeySelective(dataKousaoyi);
		} catch (Exception e) {
			logger.error("添加口扫仪失败", e);
			Result.error();
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result delete(String ids) {
		String[] split = ids.split(",");
		try {
			for (String id : split) {
				DataKousaoyi dataKousaoyi = new DataKousaoyi();
				dataKousaoyi.setId(Long.parseLong(id));
				dataKousaoyi.setStatus(0);
				dataKousaoyiMapper.updateByPrimaryKeySelective(dataKousaoyi);
			}
		} catch (NumberFormatException e) {
			logger.error("删除口扫仪失败", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	} 
	
}
