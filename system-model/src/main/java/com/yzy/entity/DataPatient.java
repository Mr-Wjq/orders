package com.yzy.entity;

import java.util.Date;

import javax.persistence.*;

@Table(name = "data_patient")
public class DataPatient {
    /**
     * 病历号
     */
    @Id
    private Long id;
    /**
     * 创建者id
     */
    @Column(name = "create_id")
    private Long createId;

    /**
     * 患者姓名
     */
    @Column(name = "patient_name")
    private String patientName;

    /**
     * 年龄
     */
    @Column(name = "patient_age")
    private Integer patientAge;

    /**
     * 患者性别1男2女
     */
    @Column(name = "patient_sex")
    private Integer patientSex;

    /**
     * 手机号
     */
    @Column(name = "patient_phone")
    private String patientPhone;

    /**
     * 患者类别1初诊2复诊
     */
    @Column(name = "patient_type")
    private Integer patientType;

    /**
     * 治疗类型id
     */
    @Column(name = "base_cure_id")
    private Long baseCureId;

    /**
     * 状态0:删除/1:正常
     */
    private Integer status;

    private Date createTime;
    private Date updateTime;
    /**
     * 获取病历号
     *
     * @return id - 病历号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置病历号
     *
     * @param id 病历号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取患者姓名
     *
     * @return patient_name - 患者姓名
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * 设置患者姓名
     *
     * @param patientName 患者姓名
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * 获取年龄
     *
     * @return patient_age - 年龄
     */
    public Integer getPatientAge() {
        return patientAge;
    }

    /**
     * 设置年龄
     *
     * @param patientAge 年龄
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
     * 获取手机号
     *
     * @return patient_phone - 手机号
     */
    public String getPatientPhone() {
        return patientPhone;
    }

    /**
     * 设置手机号
     *
     * @param patientPhone 手机号
     */
    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
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
     * 获取状态0:删除/1:正常
     *
     * @return status - 状态0:删除/1:正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0:删除/1:正常
     *
     * @param status 状态0:删除/1:正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
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
}