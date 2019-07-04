package com.yzy.entity.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.yzy.utils.DateUtils;

public class UserUnitVO {

	private Long userId;
	private String loginName;
	private String zhName;
	private String phone;
	private String email;
	private Long createBy;
	private Long updateBy;
	private Integer status;
	private Integer isFinal;
	private Date createTime;
    private Long unitId;
    private String unitName;
    private Integer unitType;
    private Integer unitProvinceId;
    private String unitProvince;
    private Integer unitCityId;
    private String unitCity;
    private Integer unitDistrictId;
    private String unitDistrict;
    private String unitAddress;
    private Long roleId;
	private String roleName;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getZhName() {
		return zhName;
	}
	public void setZhName(String zhName) {
		this.zhName = zhName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsFinal() {
		return isFinal;
	}
	public void setIsFinal(Integer isFinal) {
		this.isFinal = isFinal;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		
		this.createTime = createTime;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
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
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "UserUnitVO [userId=" + userId + ", loginName=" + loginName + ", zhName=" + zhName + ", phone=" + phone
				+ ", email=" + email + ", createBy=" + createBy + ", updateBy=" + updateBy + ", status=" + status
				+ ", isFinal=" + isFinal + ", createTime=" + createTime + ", unitId=" + unitId + ", unitName="
				+ unitName + ", unitType=" + unitType + ", unitProvinceId=" + unitProvinceId + ", unitProvince="
				+ unitProvince + ", unitCityId=" + unitCityId + ", unitCity=" + unitCity + ", unitDistrictId="
				+ unitDistrictId + ", unitDistrict=" + unitDistrict + ", unitAddress=" + unitAddress + ", roleId="
				+ roleId + ", roleName=" + roleName + "]";
	}
	
	
}
