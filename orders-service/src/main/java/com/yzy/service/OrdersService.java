package com.yzy.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yzy.entity.DataOrders;
import com.yzy.entity.vo.OrdersVO;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

public interface OrdersService {

	Result createOrders(DataOrders dataOrders,MultipartFile accessoryFile);

	LayuiTable selectDoctorOrders(int page, int limit, String ordersNum, String patientName, String receiveUnitName,
			Integer status, String createTime);

	OrdersVO selectOrdersById(Long ordersId);

	Result updateOrders(DataOrders dataOrders, MultipartFile accessoryFile);

	Result ordersDetails(Long ordersId);

	void downloadAccessory(Long ordersId, HttpServletResponse response);

	Result updateOrdersStatus(DataOrders dataOrders);

	LayuiTable selectFactoryOrders(int page, int limit, String ordersNum, String patientName, String createUnitName,
			Integer status, String createTime);

	LayuiTable selectSystemOrders(int page, int limit, String ordersNum, String patientName, String createUnitName,
			String receiveUnitName, Integer status, String createTime);

}
