package com.yzy.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "data_orders")
public class DataOrders {
    @Id
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "orders_num")
    private String ordersNum;

    /**
     * 订单发布单位ID
     */
    @Column(name = "create_unit_id")
    private Long createUnitId;

    /**
     * 订单发布者id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 订单接收单位ID(工厂ID)
     */
    @Column(name = "receive_unit_id")
    private Long receiveUnitId;

    /**
     * 订单接收者id(工厂员工)
     */
    @Column(name = "receive_user_id")
    private Long receiveUserId;

    /**
     * 患者id
     */
    @Column(name = "data_patient_id")
    private Long dataPatientId;

    /**
     * 接受时间
     */
    @Column(name = "receive_time")
    private Date receiveTime;

    /**
     * 价格
     */
    private BigDecimal totalPrices;
    private BigDecimal actualPayment;

    /**
     * 拒绝理由
     */
    @Column(name = "refuse_reason")
    private String refuseReason;

    /**
     * 发货时间
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 快递名称
     */
    @Column(name = "express_name")
    private String expressName;

    /**
     * 快递号
     */
    @Column(name = "express_num")
    private String expressNum;

    /**
     * 订单附件:1扫描数据2颌架3咬蜡4托盘5照片6旧牙7参考摸8比色板
     */
    @Column(name = "orders_accessory")
    private String ordersAccessory;

    /**
     * 扫描文件
     */
    @Column(name = "accessory_name")
    private String accessoryName;

    /**
     * 文件路径
     */
    @Column(name = "accessory_url")
    private String accessoryUrl;

    /**
     * 状态：0删除1未发布2待接单3已拒绝4已接收5生产中6已发货7订单完成
     */
    private Integer status;
    private Integer payStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 备注
     */
    private String remarks;

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
     * 获取订单号
     *
     * @return orders_num - 订单号
     */
    public String getOrdersNum() {
        return ordersNum;
    }

    /**
     * 设置订单号
     *
     * @param ordersNum 订单号
     */
    public void setOrdersNum(String ordersNum) {
        this.ordersNum = ordersNum;
    }

    /**
     * 获取订单发布单位ID
     *
     * @return create_unit_id - 订单发布单位ID
     */
    public Long getCreateUnitId() {
        return createUnitId;
    }

    /**
     * 设置订单发布单位ID
     *
     * @param createUnitId 订单发布单位ID
     */
    public void setCreateUnitId(Long createUnitId) {
        this.createUnitId = createUnitId;
    }

    /**
     * 获取订单发布者id
     *
     * @return create_user_id - 订单发布者id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置订单发布者id
     *
     * @param createUserId 订单发布者id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取订单接收单位ID(工厂ID)
     *
     * @return receive_unit_id - 订单接收单位ID(工厂ID)
     */
    public Long getReceiveUnitId() {
        return receiveUnitId;
    }

    /**
     * 设置订单接收单位ID(工厂ID)
     *
     * @param receiveUnitId 订单接收单位ID(工厂ID)
     */
    public void setReceiveUnitId(Long receiveUnitId) {
        this.receiveUnitId = receiveUnitId;
    }

    /**
     * 获取订单接收者id(工厂员工)
     *
     * @return receive_user_id - 订单接收者id(工厂员工)
     */
    public Long getReceiveUserId() {
        return receiveUserId;
    }

    /**
     * 设置订单接收者id(工厂员工)
     *
     * @param receiveUserId 订单接收者id(工厂员工)
     */
    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    /**
     * 获取患者id
     *
     * @return data_cases_id - 患者id
     */
    public Long getDataPatientId() {
        return dataPatientId;
    }

    /**
     * 设置患者id
     *
     * @param dataCasesId 患者id
     */
    public void setDataPatientId(Long dataPatientId) {
        this.dataPatientId = dataPatientId;
    }

    /**
     * 获取接受时间
     *
     * @return receive_time - 接受时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置接受时间
     *
     * @param receiveTime 接受时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 获取实际支付
     *
     */
    public BigDecimal getActualPayment() {
        return actualPayment;
    }

    /**
     * 设置实际支付
     *
     * @param actualPayment 实际支付
     */
    public void setActualPayment(BigDecimal actualPayment) {
        this.actualPayment = actualPayment;
    }

    public BigDecimal getTotalPrices() {
		return totalPrices;
	}

	public void setTotalPrices(BigDecimal totalPrices) {
		this.totalPrices = totalPrices;
	}

	/**
     * 获取拒绝理由
     *
     * @return refuse_reason - 拒绝理由
     */
    public String getRefuseReason() {
        return refuseReason;
    }

    /**
     * 设置拒绝理由
     *
     * @param refuseReason 拒绝理由
     */
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    /**
     * 获取发货时间
     *
     * @return send_time - 发货时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置发货时间
     *
     * @param sendTime 发货时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取快递名称
     *
     * @return express_name - 快递名称
     */
    public String getExpressName() {
        return expressName;
    }

    /**
     * 设置快递名称
     *
     * @param expressName 快递名称
     */
    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    /**
     * 获取快递号
     *
     * @return express_num - 快递号
     */
    public String getExpressNum() {
        return expressNum;
    }

    /**
     * 设置快递号
     *
     * @param expressNum 快递号
     */
    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    /**
     * 获取订单附件:1扫描数据2颌架3咬蜡4托盘5照片6旧牙7参考摸8比色板
     *
     * @return orders_accessory - 订单附件:1扫描数据2颌架3咬蜡4托盘5照片6旧牙7参考摸8比色板
     */
    public String getOrdersAccessory() {
        return ordersAccessory;
    }

    /**
     * 设置订单附件:1扫描数据2颌架3咬蜡4托盘5照片6旧牙7参考摸8比色板
     *
     * @param ordersAccessory 订单附件:1扫描数据2颌架3咬蜡4托盘5照片6旧牙7参考摸8比色板
     */
    public void setOrdersAccessory(String ordersAccessory) {
        this.ordersAccessory = ordersAccessory;
    }

    /**
     * 获取扫描文件
     *
     * @return accessory_name - 扫描文件
     */
    public String getAccessoryName() {
        return accessoryName;
    }

    /**
     * 设置扫描文件
     *
     * @param accessoryName 扫描文件
     */
    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    /**
     * 获取文件路径
     *
     * @return accessory_url - 文件路径
     */
    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    /**
     * 设置文件路径
     *
     * @param accessoryUrl 文件路径
     */
    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }

    /**
     * 获取状态：0删除1未发布2待接单3已拒绝4已接收5生产中6已发货7订单完成
     *
     * @return status - 状态：0删除1未发布2待接单3已拒绝4已接收5生产中6已发货7订单完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0删除1未发布2待接单3已拒绝4已接收5生产中6已发货7订单完成
     *
     * @param status 状态：0删除1未发布2待接单3已拒绝4已接收5生产中6已发货7订单完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取完成时间
     *
     * @return finish_time - 完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置完成时间
     *
     * @param finishTime 完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
    
}