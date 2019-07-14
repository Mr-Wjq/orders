package com.yzy.service;

import org.springframework.web.multipart.MultipartFile;

import com.yzy.entity.DataOrders;
import com.yzy.utils.Result;

public interface OrdersService {

	Result createOrders(DataOrders dataOrders, Integer baseCureId, String selectedTooths,
			MultipartFile accessoryFile);

}
