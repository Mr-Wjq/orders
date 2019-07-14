package com.yzy.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class DataDiscountVO {
	@Id
    private Long id;

    /**
     * 优惠券名称
     */
    @Column(name = "discount_name")
    private String discountName;

    /**
     * 优惠券额度
     */
    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    /**
     * 优惠卷类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     */
    @Column(name = "discount_type")
    private Integer discountType;

    /**
     * 0:删除1:正常
     */
    private Integer status;

    private Long factoryId;
    private String factoryName;
    
    private Long baseProductId;
    private String textureName;
    private String brandName;
    
    private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getDiscountType() {
		return discountType;
	}
	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public Long getBaseProductId() {
		return baseProductId;
	}
	public void setBaseProductId(Long baseProductId) {
		this.baseProductId = baseProductId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
}
