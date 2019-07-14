package com.yzy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "data_discount_unit")
public class DataDiscountUnit {
    @Id
    private Long id;

    /**
     * 优惠券id
     */
    @Column(name = "data_discount_id")
    private Long dataDiscountId;

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
   
    private Date createTime;

    
    private Integer status;
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
     * 获取优惠券id
     *
     * @return data_discount_id - 优惠券id
     */
    public Long getDataDiscountId() {
        return dataDiscountId;
    }

    /**
     * 设置优惠券id
     *
     * @param dataDiscountId 优惠券id
     */
    public void setDataDiscountId(Long dataDiscountId) {
        this.dataDiscountId = dataDiscountId;
    }

    /**
     * 获取单位id
     *
     * @return sys_unit_id - 单位id
     */
    public Long getSysUnitId() {
        return sysUnitId;
    }

    /**
     * 设置单位id
     *
     * @param sysUnitId 单位id
     */
    public void setSysUnitId(Long sysUnitId) {
        this.sysUnitId = sysUnitId;
    }

    /**
     * 获取优惠券张数
     *
     * @return num - 优惠券张数
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置优惠券张数
     *
     * @param num 优惠券张数
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取优惠卷类型1：无时间限制 2：有时间限制
     *
     * @return type - 优惠卷类型1：无时间限制 2：有时间限制
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置优惠卷类型1：无时间限制 2：有时间限制
     *
     * @param type 优惠卷类型1：无时间限制 2：有时间限制
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取开始使用时间
     *
     * @return start_time - 开始使用时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始使用时间
     *
     * @param startTime 开始使用时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取使用结束时间
     *
     * @return end_time - 使用结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置使用结束时间
     *
     * @param endTime 使用结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}