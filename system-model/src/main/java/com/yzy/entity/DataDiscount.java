package com.yzy.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Table(name = "data_discount")
public class DataDiscount {
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
    private Long baseProductId;
    private Date createTime;
    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取优惠券名称
     *
     * @return discount_name - 优惠券名称
     */
    public String getDiscountName() {
        return discountName;
    }

    /**
     * 设置优惠券名称
     *
     * @param discountName 优惠券名称
     */
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    /**
     * 获取优惠券额度
     *
     * @return discount_price - 优惠券额度
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 设置优惠券额度
     *
     * @param discountPrice 优惠券额度
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 获取优惠卷类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     *
     * @return discount_type - 优惠卷类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     */
    public Integer getDiscountType() {
        return discountType;
    }

    /**
     * 设置优惠卷类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     *
     * @param discountType 优惠卷类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     */
    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    /**
     * 获取0:删除1:正常
     *
     * @return status - 0:删除1:正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0:删除1:正常
     *
     * @param status 0:删除1:正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}