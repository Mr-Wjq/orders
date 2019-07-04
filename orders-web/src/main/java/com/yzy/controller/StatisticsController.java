package com.yzy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.service.StatisticsService;
import com.yzy.utils.LayuiTable;

@Controller
@RequestMapping("tj")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;
	
	@RequestMapping("systemStatisticsData")
	@ResponseBody
	public LayuiTable systemStatisticsData(String statisticsType,String startTime,String endTime) {
		return statisticsService.systemStatisticsData(statisticsType,startTime,endTime);
	}
	@RequestMapping("doctorStatisticsData")
	@ResponseBody
	public LayuiTable doctorStatisticsData(String startTime,String endTime) {
		return statisticsService.doctorStatisticsData(startTime,endTime);
	}
	@RequestMapping("factoryStatisticsData")
	@ResponseBody
	public LayuiTable factoryStatisticsData(String startTime,String endTime) {
		return statisticsService.factoryStatisticsData(startTime,endTime);
	}
	
}
