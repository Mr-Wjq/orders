package com.yzy.entity;

import java.util.Date;
import javax.persistence.*;

import com.yzy.utils.DateUtils;

@Table(name = "sys_unit")
public class SysUnit {
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

    /**
     * 市id
     */
    @Column(name = "unit_city_id")
    private Integer unitCityId;

    /**
     * 区id
     */
    @Column(name = "unit_district_id")
    private Integer unitDistrictId;

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

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取父id
     *
     * @return pid - 父id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父id
     *
     * @param pid 父id
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取单位名称
     *
     * @return unit_name - 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 设置单位名称
     *
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * 获取单位类型 1医院2连锁门诊3个体门诊4工厂
     *
     * @return unit_type - 单位类型 1医院2连锁门诊3个体门诊4工厂
     */
    public Integer getUnitType() {
        return unitType;
    }

    /**
     * 设置单位类型 1医院2连锁门诊3个体门诊4工厂
     *
     * @param unitType 单位类型 1医院2连锁门诊3个体门诊4工厂
     */
    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    /**
     * 获取省id
     *
     * @return unit_province_id - 省id
     */
    public Integer getUnitProvinceId() {
        return unitProvinceId;
    }

    /**
     * 设置省id
     *
     * @param unitProvinceId 省id
     */
    public void setUnitProvinceId(Integer unitProvinceId) {
        this.unitProvinceId = unitProvinceId;
    }

    /**
     * 获取市id
     *
     * @return unit_city_id - 市id
     */
    public Integer getUnitCityId() {
        return unitCityId;
    }

    /**
     * 设置市id
     *
     * @param unitCityId 市id
     */
    public void setUnitCityId(Integer unitCityId) {
        this.unitCityId = unitCityId;
    }

    /**
     * 获取区id
     *
     * @return unit_district_id - 区id
     */
    public Integer getUnitDistrictId() {
        return unitDistrictId;
    }

    /**
     * 设置区id
     *
     * @param unitDistrictId 区id
     */
    public void setUnitDistrictId(Integer unitDistrictId) {
        this.unitDistrictId = unitDistrictId;
    }

    /**
     * 获取单位详细地址
     *
     * @return unit_address - 单位详细地址
     */
    public String getUnitAddress() {
        return unitAddress;
    }

    /**
     * 设置单位详细地址
     *
     * @param unitAddress 单位详细地址
     */
    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    /**
     * 获取数据状态,0:删除/1:正常
     *
     * @return status - 数据状态,0:删除/1:正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置数据状态,0:删除/1:正常
     *
     * @param status 数据状态,0:删除/1:正常
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
     * 获取is是否允许修改 1允许2不允许
     *
     * @return is_final - is是否允许修改 1允许2不允许
     */
    public Integer getIsFinal() {
        return isFinal;
    }

    /**
     * 设置is是否允许修改 1允许2不允许
     *
     * @param isFinal is是否允许修改 1允许2不允许
     */
    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }
}