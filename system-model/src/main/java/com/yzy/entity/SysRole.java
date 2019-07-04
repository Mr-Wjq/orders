package com.yzy.entity;

import java.util.Date;

import com.yzy.utils.DateUtils;

/**
 * @Author: 王俊强
 * @Description:
 */
public class SysRole {

    // id :
    private Long id;

    // description :
    private String description;

    // name :
    private String name;
 
    // name :
    private Long parentId;

    // rank :排序
    private Long rank;

    // is_final :是否可删除
    private Integer isFinal;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
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

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return DateUtils.convertDateToStr(updateTime);
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

	public SysRole(Long id, String description, String name, Long parentId, Long rank, Integer isFinal,
			Date createTime, Date updateTime, Long createBy, Long updateBy, Integer status) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
		this.parentId = parentId;
		this.rank = rank;
		this.isFinal = isFinal;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.status = status;
	}

	public SysRole() {
		super();
	}

	@Override
	public String toString() {
		return "SysRole [id=" + id + ", description=" + description + ", name=" + name + ", parentId=" + parentId
				+ ", rank=" + rank + ", isFinal=" + isFinal + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", createBy=" + createBy + ", updateBy=" + updateBy + ", status=" + status + "]";
	}
}
