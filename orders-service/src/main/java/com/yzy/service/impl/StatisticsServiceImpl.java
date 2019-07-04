package com.yzy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzy.dao.DataOrdersMapper;
import com.yzy.dao.SysUnitMapper;
import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUnit;
import com.yzy.entity.vo.UnitOrdersNum;
import com.yzy.entity.vo.UnitStatistics;
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
		List<UnitOrdersNum> list = new ArrayList<UnitOrdersNum>();
		if("doctor".equals(statisticsType)) {
			list = dataOrdersMapper.selectDoctorOrdersNum( startTime,  endTime);
		}else if("factory".equals(statisticsType)) {
			list = dataOrdersMapper.selectFactoryOrdersNum( startTime,  endTime);
		}
		ArrayList<UnitStatistics> UnitStatisticsList = new ArrayList<UnitStatistics>();
		Integer xiufuSum = 0;
		Integer zhengjiSum = 0;
		Integer zhongzhiSum = 0;
		for (UnitOrdersNum unitOrdersNum : list) {
			UnitStatistics unitStatistics = new UnitStatistics();
			SysUnit sysUnit = sysUnitMapper.selectByPrimaryKey(unitOrdersNum.getUnitId());
			unitStatistics.setUnitName(sysUnit.getUnitName());
			if("doctor".equals(statisticsType)) {
				Integer xiufuNum = dataOrdersMapper.selectCureNum((long)2,unitOrdersNum.getUnitId(),null,null,startTime,  endTime);
				xiufuSum += xiufuNum;
				unitStatistics.setXiufu(xiufuNum);
				Integer zhengjiNum = dataOrdersMapper.selectCureNum((long)3,unitOrdersNum.getUnitId(),null,null,startTime,  endTime);
				zhengjiSum += zhengjiNum;
				unitStatistics.setZhengji(zhengjiNum);
				Integer zhongzhiNum = dataOrdersMapper.selectCureNum((long)4,unitOrdersNum.getUnitId(),null,null,startTime,  endTime);
				zhongzhiSum += zhongzhiNum;
				unitStatistics.setZhongzhi(zhongzhiNum);
			}else if("factory".equals(statisticsType)) {
				Integer xiufuNum = dataOrdersMapper.selectCureNum((long)2,null,unitOrdersNum.getUnitId(),null,startTime,  endTime);
				unitStatistics.setXiufu(xiufuNum);
				xiufuSum += xiufuNum;
				Integer zhengjiNum = dataOrdersMapper.selectCureNum((long)3,null,unitOrdersNum.getUnitId(),null,startTime,  endTime);
				unitStatistics.setZhengji(zhengjiNum);
				zhengjiSum += zhengjiNum;
				Integer zhongzhiNum = dataOrdersMapper.selectCureNum((long)4,null,unitOrdersNum.getUnitId(),null,startTime,  endTime);
				unitStatistics.setZhongzhi(zhongzhiNum);
				zhongzhiSum += zhongzhiNum;
			}
			UnitStatisticsList.add(unitStatistics);
		}
		UnitStatistics unitStatistics = new UnitStatistics();
		unitStatistics.setUnitName("合计");
		unitStatistics.setXiufu(xiufuSum);
		unitStatistics.setZhengji(zhengjiSum);
		unitStatistics.setZhongzhi(zhongzhiSum);
		UnitStatisticsList.add(unitStatistics);
		return LayuiTable.success((long)UnitStatisticsList.size(), UnitStatisticsList);
	}


	@Override
	public LayuiTable doctorStatisticsData(String startTime, String endTime) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		/*List<SysUnit> unitList = sysUnitService.getSubjectionUnit();
		//单位ID集合
        List<Long> unitIdList = new ArrayList<>();
        for (SysUnit sysUnit:unitList) {
            unitIdList.add(sysUnit.getId());
        }*/
        Long createUserId = null;
        Long createUnitId = null;
        if(loginInfo.getRoleId() == 2) {
        	createUnitId =  loginInfo.getUnitId();
        }else if(loginInfo.getRoleId() == 3) {
        	createUserId = loginInfo.getId();
        }
        List<UnitOrdersNum> list  = dataOrdersMapper.selectDoctorOrdersStatistics(createUserId,createUnitId, startTime,  endTime);
        ArrayList<UnitStatistics> UnitStatisticsList = new ArrayList<UnitStatistics>();
		Integer xiufuSum = 0;
		Integer zhengjiSum = 0;
		Integer zhongzhiSum = 0;
		for (UnitOrdersNum unitOrdersNum : list) {
			UnitStatistics unitStatistics = new UnitStatistics();
			SysUnit sysUnit = sysUnitMapper.selectByPrimaryKey(unitOrdersNum.getUnitId());
			unitStatistics.setUnitName(sysUnit.getUnitName());
			
			Integer xiufuNum = dataOrdersMapper.selectCureNum((long)2,null,unitOrdersNum.getUnitId(),createUserId,startTime,  endTime);
			unitStatistics.setXiufu(xiufuNum);
			xiufuSum += xiufuNum;
			Integer zhengjiNum = dataOrdersMapper.selectCureNum((long)3,null,unitOrdersNum.getUnitId(),createUserId,startTime,  endTime);
			unitStatistics.setZhengji(zhengjiNum);
			zhengjiSum += zhengjiNum;
			Integer zhongzhiNum = dataOrdersMapper.selectCureNum((long)4,null,unitOrdersNum.getUnitId(),createUserId,startTime,  endTime);
			unitStatistics.setZhongzhi(zhongzhiNum);
			zhongzhiSum += zhongzhiNum;
			
			UnitStatisticsList.add(unitStatistics);
		}
		UnitStatistics unitStatistics = new UnitStatistics();
		unitStatistics.setUnitName("合计");
		unitStatistics.setXiufu(xiufuSum);
		unitStatistics.setZhengji(zhengjiSum);
		unitStatistics.setZhongzhi(zhongzhiSum);
		UnitStatisticsList.add(unitStatistics);
		return LayuiTable.success((long)UnitStatisticsList.size(), UnitStatisticsList);
	}


	@Override
	public LayuiTable factoryStatisticsData(String startTime, String endTime) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		/*List<SysUnit> unitList = sysUnitService.getSubjectionUnit();
		//单位ID集合
        List<Long> unitIdList = new ArrayList<>();
        for (SysUnit sysUnit:unitList) {
            unitIdList.add(sysUnit.getId());
        }*/
      
        List<UnitOrdersNum> list  = dataOrdersMapper.selectFactoryOrdersStatistics(loginInfo.getUnitId(), startTime,  endTime);
        ArrayList<UnitStatistics> UnitStatisticsList = new ArrayList<UnitStatistics>();
		Integer xiufuSum = 0;
		Integer zhengjiSum = 0;
		Integer zhongzhiSum = 0;
		for (UnitOrdersNum unitOrdersNum : list) {
			UnitStatistics unitStatistics = new UnitStatistics();
			SysUnit sysUnit = sysUnitMapper.selectByPrimaryKey(unitOrdersNum.getUnitId());
			unitStatistics.setUnitName(sysUnit.getUnitName());
			
			Integer xiufuNum = dataOrdersMapper.selectCureNum((long)2,unitOrdersNum.getUnitId(),null,null,startTime,  endTime);
			xiufuSum += xiufuNum;
			unitStatistics.setXiufu(xiufuNum);
			Integer zhengjiNum = dataOrdersMapper.selectCureNum((long)3,unitOrdersNum.getUnitId(),null,null,startTime,  endTime);
			zhengjiSum += zhengjiNum;
			unitStatistics.setZhengji(zhengjiNum);
			Integer zhongzhiNum = dataOrdersMapper.selectCureNum((long)4,unitOrdersNum.getUnitId(),null,null,startTime,  endTime);
			zhongzhiSum += zhongzhiNum;
			unitStatistics.setZhongzhi(zhongzhiNum);
			
			UnitStatisticsList.add(unitStatistics);
		}
		UnitStatistics unitStatistics = new UnitStatistics();
		unitStatistics.setUnitName("合计");
		unitStatistics.setXiufu(xiufuSum);
		unitStatistics.setZhengji(zhengjiSum);
		unitStatistics.setZhongzhi(zhongzhiSum);
		UnitStatisticsList.add(unitStatistics);
		return LayuiTable.success((long)UnitStatisticsList.size(), UnitStatisticsList);
	}
}
