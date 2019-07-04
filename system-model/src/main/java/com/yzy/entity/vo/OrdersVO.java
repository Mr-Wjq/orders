package com.yzy.entity.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.yzy.utils.DateUtils;

public class OrdersVO {
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
    private String createUnitName;

    /**
     * 订单发布者id
     */
    @Column(name = "create_user_id")
    private Long createUserId;
    private String createUserName;

    /**
     * 订单接收单位ID(工厂ID)
     */
    @Column(name = "receive_unit_id")
    private Long receiveUnitId;
    private String receiveUnitName;

    /**
     * 订单接收者id(工厂员工)
     */
    @Column(name = "receive_user_id")
    private Long receiveUserId;
    private String receiveUserName;

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
    private String baseCureName;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrdersNum() {
		return ordersNum;
	}

	public void setOrdersNum(String ordersNum) {
		this.ordersNum = ordersNum;
	}

	public Long getCreateUnitId() {
		return createUnitId;
	}

	public void setCreateUnitId(Long createUnitId) {
		this.createUnitId = createUnitId;
	}

	public String getCreateUnitName() {
		return createUnitName;
	}

	public void setCreateUnitName(String createUnitName) {
		this.createUnitName = createUnitName;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Long getReceiveUnitId() {
		return receiveUnitId;
	}

	public void setReceiveUnitId(Long receiveUnitId) {
		this.receiveUnitId = receiveUnitId;
	}

	public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public Long getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Long receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Long getBaseCureId() {
		return baseCureId;
	}

	public void setBaseCureId(Long baseCureId) {
		this.baseCureId = baseCureId;
	}

	public String getBaseCureName() {
		return baseCureName;
	}

	public void setBaseCureName(String baseCureName) {
		this.baseCureName = baseCureName;
	}

	public Long getBaseTextureId() {
		return baseTextureId;
	}

	public void setBaseTextureId(Long baseTextureId) {
		this.baseTextureId = baseTextureId;
	}

	public Long getBaseBrandId() {
		return baseBrandId;
	}

	public void setBaseBrandId(Long baseBrandId) {
		this.baseBrandId = baseBrandId;
	}

	public Long getBaseProductId() {
		return baseProductId;
	}

	public void setBaseProductId(Long baseProductId) {
		this.baseProductId = baseProductId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNum() {
		return expressNum;
	}

	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

	public Integer getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(Integer patientSex) {
		this.patientSex = patientSex;
	}

	public Integer getPatientType() {
		return patientType;
	}

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOrdersAccessory() {
		return ordersAccessory;
	}

	public void setOrdersAccessory(String ordersAccessory) {
		this.ordersAccessory = ordersAccessory;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public String getAccessoryUrl() {
		return accessoryUrl;
	}

	public void setAccessoryUrl(String accessoryUrl) {
		this.accessoryUrl = accessoryUrl;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "OrdersVO [id=" + id + ", ordersNum=" + ordersNum + ", createUnitId=" + createUnitId
				+ ", createUnitName=" + createUnitName + ", createUserId=" + createUserId + ", createUserName="
				+ createUserName + ", receiveUnitId=" + receiveUnitId + ", receiveUnitName=" + receiveUnitName
				+ ", receiveUserId=" + receiveUserId + ", receiveUserName=" + receiveUserName + ", receiveTime="
				+ receiveTime + ", baseCureId=" + baseCureId + ", baseCureName=" + baseCureName + ", baseTextureId="
				+ baseTextureId + ", baseBrandId=" + baseBrandId + ", baseProductId=" + baseProductId + ", price="
				+ price + ", refuseReason=" + refuseReason + ", sendTime=" + sendTime + ", expressName=" + expressName
				+ ", expressNum=" + expressNum + ", patientName=" + patientName + ", patientAge=" + patientAge
				+ ", patientSex=" + patientSex + ", patientType=" + patientType + ", toothPosition1=" + toothPosition1
				+ ", toothPosition2=" + toothPosition2 + ", toothPosition3=" + toothPosition3 + ", toothPosition4="
				+ toothPosition4 + ", color=" + color + ", ordersAccessory=" + ordersAccessory + ", accessoryName="
				+ accessoryName + ", accessoryUrl=" + accessoryUrl + ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", finishTime=" + finishTime + ", remarks=" + remarks + "]";
	}

	
    
}
