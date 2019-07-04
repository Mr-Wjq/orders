package com.yzy.service;

import java.util.List;

import com.yzy.entity.BaseCountry;

public interface BaseCountryService {
	List<BaseCountry> getCountryByPid(Integer pid);
}
