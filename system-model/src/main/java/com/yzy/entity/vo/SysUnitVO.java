package com.yzy.entity.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class SysUnitVO {
	/**
     * 主键id
     */
    @Id
    @GeneratedValue(generator = "JDBC") //返回主键
    private Long id;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 单位名称
     */
    @Column(name = "unit_name")
    private String unitName;

    /**
     * 单位类型 1医院2连锁门诊3个体门诊4工厂
     */
    @Column(name = "unit_type")
    private Integer unitType;

    /**
     * 省id
     */
    @Column(name = "unit_province_id")
    private Integer unitProvinceId;
    private String unitProvince;
    /**
     * 市id
     */
    @Column(name = "unit_city_id")
    private Integer unitCityId;
    private String unitCity;

    /**
     * 区id
     */
    @Column(name = "unit_district_id")
    private Integer unitDistrictId;
    private String unitDistrict;

    /**
     * 单位详细地址
     */
    @Column(name = "unit_address")
    private String unitAddress;

    /**
     * 数据状态,0:删除/1:正常
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
     * is是否允许修改 1允许2不允许
     */
    @Column(name = "is_final")
    private Integer isFinal;

    private Long dataUnitFromId;
    private String fromName;
    private String fuwufei;
    private Long dataKousaoyiId;
    private String kousaoyiName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getUnitType() {
		return unitType;
	}
	public void setUnitType(Integer unitType) {
		this.unitType = unitType;
	}
	public Integer getUnitProvinceId() {
		return unitProvinceId;
	}
	public void setUnitProvinceId(Integer unitProvinceId) {
		this.unitProvinceId = unitProvinceId;
	}
	public String getUnitProvince() {
		return unitProvince;
	}
	public void setUnitProvince(String unitProvince) {
		this.unitProvince = unitProvince;
	}
	public Integer getUnitCityId() {
		return unitCityId;
	}
	public void setUnitCityId(Integer unitCityId) {
		this.unitCityId = unitCityId;
	}
	public String getUnitCity() {
		return unitCity;
	}
	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}
	public Integer getUnitDistrictId() {
		return unitDistrictId;
	}
	public void setUnitDistrictId(Integer unitDistrictId) {
		this.unitDistrictId = unitDistrictId;
	}
	public String getUnitDistrict() {
		return unitDistrict;
	}
	public void setUnitDistrict(String unitDistrict) {
		this.unitDistrict = unitDistrict;
	}
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
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
	public Integer getIsFinal() {
		return isFinal;
	}
	public void setIsFinal(Integer isFinal) {
		this.isFinal = isFinal;
	}
	public Long getDataUnitFromId() {
		return dataUnitFromId;
	}
	public void setDataUnitFromId(Long dataUnitFromId) {
		this.dataUnitFromId = dataUnitFromId;
	}
	public Long getDataKousaoyiId() {
		return dataKousaoyiId;
	}
	public void setDataKousaoyiId(Long dataKousaoyiId) {
		this.dataKousaoyiId = dataKousaoyiId;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFuwufei() {
		return fuwufei;
	}
	public void setFuwufei(String fuwufei) {
		this.fuwufei = fuwufei;
	}
	public String getKousaoyiName() {
		return kousaoyiName;
	}
	public void setKousaoyiName(String kousaoyiName) {
		this.kousaoyiName = kousaoyiName;
	}
}
