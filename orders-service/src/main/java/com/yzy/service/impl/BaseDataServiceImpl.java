package com.yzy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzy.dao.BaseBrandMapper;
import com.yzy.dao.BaseCureMapper;
import com.yzy.dao.BaseExpressMapper;
import com.yzy.dao.BaseProductMapper;
import com.yzy.dao.BaseTextureMapper;
import com.yzy.dao.DataProductPriceMapper;
import com.yzy.entity.BaseBrand;
import com.yzy.entity.BaseCure;
import com.yzy.entity.BaseExpress;
import com.yzy.entity.BaseProduct;
import com.yzy.entity.BaseTexture;
import com.yzy.entity.DataProductPrice;
import com.yzy.entity.SysUnit;
import com.yzy.entity.vo.ProductTexture;
import com.yzy.entity.vo.ProductVO;
import com.yzy.service.BaseDataService;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

import tk.mybatis.mapper.entity.Example;

@Service
public class BaseDataServiceImpl implements BaseDataService{

	private Logger logger = LoggerFactory.getLogger(BaseDataServiceImpl.class);
	
	@Autowired
	private BaseCureMapper baseCureMapper;
	@Autowired
	private BaseExpressMapper baseExpressMapper;
	@Autowired
	private BaseProductMapper baseProductMapper;

	@Autowired
	private DataProductPriceMapper dataProductPriceMapper;
	
	@Override
	public LayuiTable selectCure(int page, int limit) {

		Example example = new Example(BaseCure.class);
		example.createCriteria().andNotEqualTo("status", 0);
		PageHelper.startPage(page, limit);
		List<BaseCure> list = baseCureMapper.selectByExample(example);
		PageInfo<BaseCure> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}
	@Override
	public List<BaseCure> selectAllCureList() {
		Example example = new Example(BaseCure.class);
		example.createCriteria().andNotEqualTo("status", 0);
		List<BaseCure> list = baseCureMapper.selectByExample(example);
		return list;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result insertCure(BaseCure baseCure) {
		try {
			baseCureMapper.insertSelective(baseCure);
		} catch (Exception e) {
			logger.error("添加治疗类型出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result deleteCure(String ids) {
		try {
			if(StringUtils.isNotBlank(ids)) {
				String[] split = ids.split(",");
				int length = split.length;
				for (int i = 0; i < length; i++) {
					BaseCure baseCure = new BaseCure();
					baseCure.setId(Long.parseLong(split[i]));
					baseCure.setStatus(0);
					baseCureMapper.updateByPrimaryKeySelective(baseCure);
					
			/*		BaseTexture baseTexture = new BaseTexture();
					baseTexture.setStatus(0);
					Example example = new Example(BaseTexture.class);
					example.createCriteria().andEqualTo("baseCureId",Long.parseLong(split[i]));
					baseTextureMapper.updateByExample(baseTexture, example);
					
					BaseBrand baseBrand = new BaseBrand();
					baseBrand.setStatus(0);
					Example example2 = new Example(BaseBrand.class);
					example2.createCriteria().andEqualTo("baseCureId",Long.parseLong(split[i]));
					baseBrandMapper.updateByExample(baseBrand, example2);*/
					
					BaseProduct baseProduct = new BaseProduct();
					baseProduct.setStatus(0);
					Example example3 = new Example(BaseProduct.class);
					example3.createCriteria().andEqualTo("baseCureId",Long.parseLong(split[i]));
					baseProductMapper.updateByExample(baseProduct, example3);
				}
			}
		} catch (Exception e) {
			logger.error("删除治疗类型出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateCure(BaseCure baseCure) {
		try {
			baseCureMapper.updateByPrimaryKeySelective(baseCure);
		} catch (Exception e) {
			logger.error("修改治疗类型出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}

	@Override
	public LayuiTable selectProduct(int page, int limit, Long cureId, String productName, String textureName,
			String brandName) {
		PageHelper.startPage(page, limit);
		List<ProductVO> list = baseProductMapper.selectProductVO( cureId,  productName,  textureName, brandName);
		PageInfo<ProductVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
 		return LayuiTable.success(sum, list);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result insertProduct(BaseProduct baseProduct) {
		try {
			baseProductMapper.insertSelective(baseProduct);
		} catch (Exception e) {
			logger.error("新增产品出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateProduct(BaseProduct baseProduct) {
		try {
			baseProductMapper.updateByPrimaryKeySelective(baseProduct);
		} catch (Exception e) {
			logger.error("修改产品出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result deleteProduct(String ids) {
		try {
			if(StringUtils.isNotBlank(ids)) {
				String[] split = ids.split(",");
				int length = split.length;
				for (int i = 0; i < length; i++) {
					BaseProduct baseProduct = new BaseProduct();
					baseProduct.setStatus(0);
					baseProduct.setId(Long.parseLong(split[i]));
					baseProductMapper.updateByPrimaryKeySelective(baseProduct);
				}
			}
		} catch (Exception e) {
			logger.error("删除产品名称出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}
	@Override
	public Result selectProductByCureId(Long cureId) {
		
		return null;
	}
	@Override
	public LayuiTable selectFactoryProduct(int page,int limit,Long cureId,String productName,String textureName,String brandName,Long unitId) {
		PageHelper.startPage(page, limit);
		List<ProductVO> list = baseProductMapper.selectFactoryProductVO( cureId,  productName,  textureName, brandName,unitId,null);
		PageInfo<ProductVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
 		return LayuiTable.success(sum, list);
	}
	
	@Override
	public LayuiTable selectNotAddProductVO(int page,int limit,Long cureId,String productName,String textureName,String brandName,Long unitId) {
		PageHelper.startPage(page, limit);
		List<ProductVO> list = baseProductMapper.selectNotAddProductVO( cureId,  productName,  textureName, brandName,unitId);
		PageInfo<ProductVO> pageInfo = new PageInfo<>(list);   
		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result factoryAddProduct(String ids, Long unitId) {
			try {
				String[] split = ids.split(",");
				int length = split.length;
				for (int i = 0; i < length; i++) {
					DataProductPrice dataProductPrice = new DataProductPrice();
					dataProductPrice.setFactoryId(unitId);
					dataProductPrice.setBaseProductId(Long.parseLong(split[i]));
					dataProductPriceMapper.insertSelective(dataProductPrice);
				}
			} catch (NumberFormatException e) {
				logger.error("工厂添加产品出错",e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return Result.error("系统繁忙请稍后重试");
			}
		return Result.success();
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result factoryDeleteProduct(String ids) {
		try {
			String[] split = ids.split(",");
			int length = split.length;
			for (int i = 0; i < length; i++) {
				dataProductPriceMapper.deleteByPrimaryKey(split[i]);
			}
		} catch (NumberFormatException e) {
			logger.error("工厂删除产品出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result factoryUpdateProductPrice(Long id, String price) {
		DataProductPrice dataProductPrice = new DataProductPrice();
		dataProductPrice.setId(id);
		dataProductPrice.setPrice(price);
		try {
			dataProductPriceMapper.updateByPrimaryKeySelective(dataProductPrice);
		} catch (Exception e) {
			logger.error("工厂修改产品价格出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}
	@Override
	public List<ProductTexture> selectProductTexture(Long baseCureId) {
		
		ArrayList<ProductTexture> arrayList = new ArrayList<ProductTexture>();
		Example example = new Example(BaseProduct.class);
		example.createCriteria().andEqualTo("baseCureId", baseCureId).andNotEqualTo("status", 0);
		List<BaseProduct> list = baseProductMapper.selectByExample(example);
		for (BaseProduct baseProduct : list) {
			ProductTexture productTexture = new ProductTexture();
			productTexture.setId(baseProduct.getId());
			StringBuffer buffer = new StringBuffer();
			if(StringUtils.isNotBlank(baseProduct.getTextureName())) {
				buffer.append("<span style='font-size:50px;' >材质:"+baseProduct.getTextureName()+"</span>");
			}else {
				buffer.append("材质:无");
			}
			if(StringUtils.isNotBlank(baseProduct.getBrandName())) {
				buffer.append("<br>--品牌:"+baseProduct.getBrandName());
			}else {
				buffer.append("--品牌:无");
			}
			if(StringUtils.isNotBlank(baseProduct.getProductName())) {
				buffer.append("--产品名称:"+baseProduct.getProductName());
			}else {
				buffer.append("--产品名称:无");
			}
			productTexture.setName(buffer.toString());
			arrayList.add(productTexture);
		}
		return arrayList;
	}
	@Override
	public LayuiTable selectFactoryProductForDoctor(int page, int limit, Long cureId, String productName,
			String textureName, String brandName, String factoryName) {
		PageHelper.startPage(page, limit);
		List<ProductVO> list = baseProductMapper.selectFactoryProductVO( cureId,  productName,  textureName, brandName,null,factoryName);
		PageInfo<ProductVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
 		return LayuiTable.success(sum, list);
	}
	@Override
	public List<BaseExpress> selectBaseExpress() {
		return baseExpressMapper.selectAll();
	}
}
