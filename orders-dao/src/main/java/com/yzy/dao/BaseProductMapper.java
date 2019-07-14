package com.yzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.BaseProduct;
import com.yzy.entity.vo.OrdersProductVO;
import com.yzy.entity.vo.ProductVO;
import com.yzy.utils.MyMapper;

public interface BaseProductMapper extends MyMapper<BaseProduct> {

	List<ProductVO> selectProductVO(@Param("cureId")Long cureId, 
									@Param("productName")String productName, 
									@Param("textureName")String textureName, 
									@Param("brandName")String brandName);

	List<ProductVO> selectFactoryProductVO(@Param("cureId")Long cureId, 
									@Param("productName")String productName, 
									@Param("textureName")String textureName, 
									@Param("brandName")String brandName,
									@Param("factoryName")String factoryName,
									@Param("factoryUnitId")Long factoryUnitId);
	
	List<ProductVO> selectNotAddProductVO(@Param("cureId")Long cureId, 
									@Param("productName")String productName, 
									@Param("textureName")String textureName, 
									@Param("brandName")String brandName,
									@Param("unitId")Long unitId);

	List<ProductVO> selectFactoryProductForDoctor(@Param("cureId")Long cureId, 
									@Param("productName")String productName, 
									@Param("textureName")String textureName, 
									@Param("brandName")String brandName,
									@Param("factoryName")String factoryName,
									@Param("currUnitId")Long currUnitId);

	List<OrdersProductVO> selectOrdersProductVO(@Param("cureId")Long baseCureId,@Param("currUnitId") Long currUnitId);
}