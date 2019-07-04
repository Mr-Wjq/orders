package com.yzy.service;

import java.util.List;

import com.yzy.entity.BaseCure;
import com.yzy.entity.BaseExpress;
import com.yzy.entity.BaseProduct;
import com.yzy.entity.vo.ProductTexture;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

public interface BaseDataService {

	LayuiTable selectCure(int page, int limit);
	
	Result insertCure(BaseCure baseCure);

	Result deleteCure(String ids);

	Result updateCure(BaseCure baseCure);

	LayuiTable selectProduct(int page, int limit, Long cureId, String productName, String textureName,
			String brandName);

	List<BaseCure> selectAllCureList();

	Result insertProduct(BaseProduct baseProduct);

	Result updateProduct(BaseProduct baseProduct);
	Result deleteProduct(String ids);

	Result selectProductByCureId(Long cureId);

	LayuiTable selectFactoryProduct(int page,int limit,Long cureId,String productName,String textureName,String brandName,Long unitId);
	LayuiTable selectNotAddProductVO(int page,int limit,Long cureId,String productName,String textureName,String brandName,Long unitId);

	Result factoryAddProduct(String ids, Long unitId);

	Result factoryDeleteProduct(String ids);

	Result factoryUpdateProductPrice(Long id, String price);

	List<ProductTexture> selectProductTexture(Long baseCureId);

	LayuiTable selectFactoryProductForDoctor(int page, int limit, Long cureId, String productName, String textureName,
			String brandName, String factoryName);

	List<BaseExpress> selectBaseExpress();
}
