package com.yzy.entity.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class DataDiscountUnitVO {
	@Id
    private Long id;

    /**
     * 优惠券id
     */
    @Column(name = "data_discount_id")
    private Long dataDiscountId;
    private String discountName;
    private Double discountPrice;
    private Integer discountType;

    private Long factoryId;
    private String factoryName;
    
    private Long baseProductId;
    private String textureName;
    private String brandName;
    /**
     * 单位id
     */
    @Column(name = "sys_unit_id")
    private Long sysUnitId;

    /**
     * 优惠券张数
     */
    private Integer num;

    /**
     * 优惠卷类型1：无时间限制 2：有时间限制
     */
    private Integer type;

    /**
     * 开始使用时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 使用结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDataDiscountId() {
		return dataDiscountId;
	}

	public void setDataDiscountId(Long dataDiscountId) {
		this.dataDiscountId = dataDiscountId;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public Long getSysUnitId() {
		return sysUnitId;
	}

	public void setSysUnitId(Long sysUnitId) {
		this.sysUnitId = sysUnitId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
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
