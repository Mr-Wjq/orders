package com.yzy.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
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
import com.yzy.dao.DataPatientMapper;
import com.yzy.entity.DataPatient;
import com.yzy.entity.DataUnitFrom;
import com.yzy.entity.LoginInfo;
import com.yzy.service.DataPatientService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;
import com.yzy.utils.SystemConstant;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DataPatientServiceImpl implements DataPatientService {
	private static Logger logger = LoggerFactory.getLogger(DataPatientServiceImpl.class);
	@Autowired
	private DataPatientMapper dataPatientMapper; 
	
	@Override
	public LayuiTable select(int page, int limit, String patientName, String phone) {
		Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		
		Example example = new Example(DataPatient.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("createId", loginInfo.getUnitId()).andNotEqualTo("status", 0);
		if(StringUtils.isNotBlank(patientName)) {
			criteria.andLike("patientName", "%"+patientName+"%");
		}
		if(StringUtils.isNotBlank(phone)) {
			criteria.andLike("patientPhone", "%"+phone+"%");
		}
		example.setOrderByClause("create_time DESC");
		PageHelper.startPage(page, limit);
		List<DataPatient> list = dataPatientMapper.selectByExample(example);
		PageInfo<DataPatient> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}

	@Override
	public Result insert(DataPatient dataPatient) {
		Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
    	dataPatient.setCreateId(loginInfo.getUnitId());
    	dataPatient.setCreateTime(new Date());
    	try {
			dataPatientMapper.insertSelective(dataPatient);
		} catch (Exception e) {
			logger.error("添加患者失败");
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}

	@Override
	public Result update(DataPatient dataPatient) {
    	try {
    		dataPatient.setUpdateTime(new Date());
			dataPatientMapper.updateByPrimaryKeySelective(dataPatient);
		} catch (Exception e) {
			logger.error("修改患者失败");
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
				DataPatient dataPatient = new DataPatient();
				dataPatient.setId(Long.parseLong(id));
				dataPatient.setStatus(0);
				dataPatientMapper.updateByPrimaryKeySelective(dataPatient);
			}
		} catch (NumberFormatException e) {
			logger.error("删除患者失败", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		return Result.success();
	}

	@Override
	public List<DataPatient> selectPatientByCurrUnitId(Long unitId) {
		Example example = new Example(DataPatient.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("createId", unitId).andNotEqualTo("status", 0);
		example.setOrderByClause("create_time DESC");
		List<DataPatient> list = dataPatientMapper.selectByExample(example);
		return list;
	}

}
