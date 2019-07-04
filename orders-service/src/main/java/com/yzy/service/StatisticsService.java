package com.yzy.service;

import com.yzy.utils.LayuiTable;

public interface StatisticsService {

	LayuiTable systemStatisticsData(String statisticsType, String startTime, String endTime);

	LayuiTable doctorStatisticsData(String startTime, String endTime);

	LayuiTable factoryStatisticsData(String startTime, String endTime);

}
