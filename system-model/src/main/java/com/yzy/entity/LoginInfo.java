package com.yzy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 王俊强
 * @Description:
 */
public class LoginInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7054251203932104283L;

	private Long id;

    // login_name :登陆名
    private String loginName;

    // zh_name :中文名
    private String zhName;

    private String userPhoto;
    
    // email :邮箱
    private String email;

    // phone :电话
    private String phone;

    private Long roleId;

    private String firstPage;
    
    private Long unitId;

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

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

}
