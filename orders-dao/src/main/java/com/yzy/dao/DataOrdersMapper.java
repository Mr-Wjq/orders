package com.yzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.DataOrders;
import com.yzy.entity.vo.OrdersVO;
import com.yzy.entity.vo.UnitOrdersNum;
import com.yzy.utils.MyMapper;

public interface DataOrdersMapper extends MyMapper<DataOrders> {

	List<OrdersVO> selectDoctorOrders(@Param("unitIdList")List<Long> unitIdList,
								  @Param("ordersNum")String ordersNum, 
								  @Param("patientName")String patientName, 
								  @Param("createUserId")Long createUserId, 
								  @Param("receiveUnitName")String receiveUnitName,
								  @Param("status")Integer status, 
								  @Param("createTime")String createTime);
	List<OrdersVO> selectFactoryOrders(@Param("unitIdList")List<Long> unitIdList,
								  @Param("ordersNum")String ordersNum, 
								  @Param("patientName")String patientName, 
								  @Param("receiveUserId")Long receiveUserId, 
								  @Param("createUnitName")String createUnitName,
								  @Param("status")Integer status, 
								  @Param("createTime")String createTime);
	OrdersVO selectOrdersById(@Param("ordersId")Long ordersId);
	List<OrdersVO> selectSystemOrders(@Param("unitIdList")List<Long> unitIdList,
								  @Param("ordersNum")String ordersNum, 
								  @Param("patientName")String patientName, 
								  @Param("createUnitName")String createUnitName, 
								  @Param("receiveUnitName")String receiveUnitName,
								  @Param("status")Integer status, 
								  @Param("createTime")String createTime);
	
	List<UnitOrdersNum> selectDoctorOrdersNum(@Param("startTime")String startTime,
											  @Param("endTime")String endTime);
	
	List<UnitOrdersNum> selectFactoryOrdersNum(@Param("startTime")String startTime, 
											   @Param("endTime")String endTime);
	
	Integer selectCureNum(@Param("baseCureId")Long baseCureId,
							 @Param("createUnitId")Long createUnitId, 
							 @Param("receiveUnitId")Long receiveUnitId,
							 @Param("createUserId")Long createUserId,
							 @Param("startTime")String startTime, 
							   @Param("endTime")String endTime);
	List<UnitOrdersNum> selectDoctorOrdersStatistics(@Param("createUserId") Long createUserId, 
														 @Param("createUnitId") Long createUnitId, 
														 @Param("startTime")String startTime, 
														 @Param("endTime")String endTime);
	List<UnitOrdersNum> selectFactoryOrdersStatistics(@Param("receiveUnitId") Long receiveUnitId, 
														 @Param("startTime")String startTime, 
														 @Param("endTime")String endTime);

}