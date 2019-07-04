package com.yzy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "data_orders")
public class DataOrders {
    @Id
    @GeneratedValue(generator = "JDBC")
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
     * 接受时间
     */
    @Column(name = "receive_time")
    private Date receiveTime;

    /**
     * 治疗类型id
     */
    @Column(name = "base_cure_id")
    private Long baseCureId;

    /**
     * 材质id
     */
    @Column(name = "base_texture_id")
    private Long baseTextureId;

    /**
     * 品牌id
     */
    @Column(name = "base_brand_id")
    private Long baseBrandId;

    /**
     * 产品id
     */
    @Column(name = "base_product_id")
    private Long baseProductId;

    /**
     * 价格
     */
    private String price;

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
     * 患者
     */
    @Column(name = "patient_name")
    private String patientName;

    /**
     * 患者年龄
     */
    @Column(name = "patient_age")
    private Integer patientAge;

    /**
     * 患者性别1男2女
     */
    @Column(name = "patient_sex")
    private Integer patientSex;

    /**
     * 患者类别1初诊2复诊
     */
    @Column(name = "patient_type")
    private Integer patientType;

    /**
     * 牙位
     */
    @Column(name = "tooth_position1")
    private String toothPosition1;
    
    @Column(name = "tooth_position2")
    private String toothPosition2;
    
    @Column(name = "tooth_position3")
    private String toothPosition3;
    
    @Column(name = "tooth_position4")
    private String toothPosition4;

    /**
     * 颜色
     */
    private String color;

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
     * 获取治疗类型id
     *
     * @return base_cure_id - 治疗类型id
     */
    public Long getBaseCureId() {
        return baseCureId;
    }

    /**
     * 设置治疗类型id
     *
     * @param baseCureId 治疗类型id
     */
    public void setBaseCureId(Long baseCureId) {
        this.baseCureId = baseCureId;
    }

    /**
     * 获取材质id
     *
     * @return base_texture_id - 材质id
     */
    public Long getBaseTextureId() {
        return baseTextureId;
    }

    /**
     * 设置材质id
     *
     * @param baseTextureId 材质id
     */
    public void setBaseTextureId(Long baseTextureId) {
        this.baseTextureId = baseTextureId;
    }

    /**
     * 获取品牌id
     *
     * @return base_brand_id - 品牌id
     */
    public Long getBaseBrandId() {
        return baseBrandId;
    }

    /**
     * 设置品牌id
     *
     * @param baseBrandId 品牌id
     */
    public void setBaseBrandId(Long baseBrandId) {
        this.baseBrandId = baseBrandId;
    }

    /**
     * 获取产品id
     *
     * @return base_product_id - 产品id
     */
    public Long getBaseProductId() {
        return baseProductId;
    }

    /**
     * 设置产品id
     *
     * @param baseProductId 产品id
     */
    public void setBaseProductId(Long baseProductId) {
        this.baseProductId = baseProductId;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(String price) {
        this.price = price;
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
     * 获取患者
     *
     * @return patient_name - 患者
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * 设置患者
     *
     * @param patientName 患者
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * 获取患者年龄
     *
     * @return patient_age - 患者年龄
     */
    public Integer getPatientAge() {
        return patientAge;
    }

    /**
     * 设置患者年龄
     *
     * @param patientAge 患者年龄
     */
    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    /**
     * 获取患者性别1男2女
     *
     * @return patient_sex - 患者性别1男2女
     */
    public Integer getPatientSex() {
        return patientSex;
    }

    /**
     * 设置患者性别1男2女
     *
     * @param patientSex 患者性别1男2女
     */
    public void setPatientSex(Integer patientSex) {
        this.patientSex = patientSex;
    }

    /**
     * 获取患者类别1初诊2复诊
     *
     * @return patient_type - 患者类别1初诊2复诊
     */
    public Integer getPatientType() {
        return patientType;
    }

    /**
     * 设置患者类别1初诊2复诊
     *
     * @param patientType 患者类别1初诊2复诊
     */
    public void setPatientType(Integer patientType) {
        this.patientType = patientType;
    }


    public String getToothPosition1() {
		return toothPosition1;
	}

	public void setToothPosition1(String toothPosition1) {
		this.toothPosition1 = toothPosition1;
	}

	public String getToothPosition2() {
		return toothPosition2;
	}

	public void setToothPosition2(String toothPosition2) {
		this.toothPosition2 = toothPosition2;
	}

	public String getToothPosition3() {
		return toothPosition3;
	}

	public void setToothPosition3(String toothPosition3) {
		this.toothPosition3 = toothPosition3;
	}

	public String getToothPosition4() {
		return toothPosition4;
	}

	public void setToothPosition4(String toothPosition4) {
		this.toothPosition4 = toothPosition4;
	}

	/**
     * 获取颜色
     *
     * @return color - 颜色
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置颜色
     *
     * @param color 颜色
     */
    public void setColor(String color) {
        this.color = color;
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
}