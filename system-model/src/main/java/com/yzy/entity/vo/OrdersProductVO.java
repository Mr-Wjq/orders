package com.yzy.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrdersProductVO {

	private Long factoryId;
	private Long baseProductId;
	private Long baseCureId;
	private BigDecimal productPrice;
	private BigDecimal discountPrice;
	private String productName;
	private String textureName;
	private String brandName;
	private Integer num;
	private Date endTime;
	public Long getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
	}
	public Long getBaseProductId() {
		return baseProductId;
	}
	public void setBaseProductId(Long baseProductId) {
		this.baseProductId = baseProductId;
	}
	public Long getBaseCureId() {
		return baseCureId;
	}
	public void setBaseCureId(Long baseCureId) {
		this.baseCureId = baseCureId;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getTextureName() {
		return textureName;
	}
	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
