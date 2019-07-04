package com.yzy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzy.dao.BaseCountryMapper;
import com.yzy.entity.BaseCountry;
import com.yzy.service.BaseCountryService;

import tk.mybatis.mapper.entity.Example;

@Service
public class BaseCountryServiceImpl implements BaseCountryService {
	
	@Autowired
	private BaseCountryMapper baseCountryMapper;



	@Override
	public List<BaseCountry> getCountryByPid(Integer pid) {
		Example example = new Example(BaseCountry.class);
		example.createCriteria().andEqualTo("pid", pid);
        List<BaseCountry> baseCountryList = baseCountryMapper.selectByExample(example);
		return baseCountryList;
	}



}
