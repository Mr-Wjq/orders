package com.yzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzy.entity.BaseProduct;
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
									@Param("unitId")Long unitId,
									@Param("factoryName")String factoryName);
	
	List<ProductVO> selectNotAddProductVO(@Param("cureId")Long cureId, 
									@Param("productName")String productName, 
									@Param("textureName")String textureName, 
									@Param("brandName")String brandName,
									@Param("unitId")Long unitId);
}