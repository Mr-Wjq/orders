package com.yzy.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzy.dao.DataOrdersMapper;
import com.yzy.dao.SysUnitMapper;

import com.yzy.service.StatisticsService;
import com.yzy.service.SysUnitService;
import com.yzy.utils.LayuiTable;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private SysUnitMapper sysUnitMapper; 
	@Autowired
	private SysUnitService sysUnitService; 
	
	@Autowired
	private DataOrdersMapper dataOrdersMapper; 
	
	
	@Override
	public LayuiTable systemStatisticsData(String statisticsType, String startTime, String endTime) {
		return LayuiTable.error(10000, "获取失败");
	}


	@Override
	public LayuiTable doctorStatisticsData(String startTime, String endTime) {
		return LayuiTable.error(10000, "获取失败");
	}


	@Override
	public LayuiTable factoryStatisticsData(String startTime, String endTime) {
		
		return LayuiTable.error(10000, "获取失败");
	}
}
