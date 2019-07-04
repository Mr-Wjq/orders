package com.yzy.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.yzy.utils.DateUtils;

/**
 * @Author: 王俊强
 * @Description:
 */
public class SysUser implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2105275941425162399L;

	@Id
	@GeneratedValue(generator = "JDBC") //返回主键
	private Long id;

	private String loginName;

	private String password;

	private String passwordSalt;

	private String userPhoto;
	
	private String zhName;

	private String phone;

	private String email;

	private Long createBy;

	private Long updateBy;

	private Integer status;

	private Integer isFinal;

	private Date createTime;

	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", loginName=" + loginName + ", password=" + password + ", passwordSalt="
				+ passwordSalt + ", userPhoto=" + userPhoto + ", zhName=" + zhName + ", phone=" + phone + ", email="
				+ email + ", createBy=" + createBy + ", updateBy=" + updateBy + ", status=" + status + ", isFinal="
				+ isFinal + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
}