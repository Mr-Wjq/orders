package com.yzy.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysPermissionTree {
	
	// id :
    private Long id;
    
    private Long pid;
    // name :名称
    private String name;

    // description :描述
    private String description;

    // code :编码
    private String code;

    // is_final :是否可删除
    private Integer isFinal;

    // rank :排序
    private Long rank;

    // create_time :创建时间
    private java.util.Date createTime;

    // update_time :更新时间
    private java.util.Date updateTime;

    // create_by :创建人id
    private Long createBy;

    // update_by :更新人id
    private Long updateBy;

    // status :数据状态,1:正常,2:删除
    private Integer status;
    
    private String state;
    
    private List<SysPermissionTree> children = new ArrayList<>();

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(Integer isFinal) {
		this.isFinal = isFinal;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
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

	public List<SysPermissionTree> getChildren() {
		return children;
	}

	public void setChildren(List<SysPermissionTree> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public SysPermissionTree(Long id, Long pid, String name, String description, String code, Integer isFinal,
			Long rank, Date createTime, Date updateTime, Long createBy, Long updateBy, Integer status, String state,
			List<SysPermissionTree> children) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.description = description;
		this.code = code;
		this.isFinal = isFinal;
		this.rank = rank;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.status = status;
		this.state = state;
		this.children = children;
	}

	public SysPermissionTree() {
		super();
	}

	@Override
	public String toString() {
		return "SysPermissionTree [id=" + id + ", pid=" + pid + ", name=" + name + ", description=" + description
				+ ", code=" + code + ", isFinal=" + isFinal + ", rank=" + rank + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createBy=" + createBy + ", updateBy=" + updateBy + ", status="
				+ status + ", state=" + state + ", children=" + children + "]";
	}
}
