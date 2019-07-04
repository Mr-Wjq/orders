package com.yzy.entity;

/**
 * @Author: 王俊强
 * @Description:
 */
public class SysRolePermission {

    // id :
    private Long id;

    // sys_permission_id :权限id
    private Long sysPermissionId;
 
    private Long sysPermissionPid;

    // sys_role_id :角色id
    private Long sysRoleId;

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

	@Override
	public String toString() {
		return "SysRolePermission [id=" + id + ", sysPermissionId=" + sysPermissionId + ", sysPermissionPid="
				+ sysPermissionPid + ", sysRoleId=" + sysRoleId + ", rank=" + rank + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createBy=" + createBy + ", updateBy=" + updateBy + ", status="
				+ status + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSysPermissionId() {
		return sysPermissionId;
	}

	public void setSysPermissionId(Long sysPermissionId) {
		this.sysPermissionId = sysPermissionId;
	}

	public Long getSysPermissionPid() {
		return sysPermissionPid;
	}

	public void setSysPermissionPid(Long sysPermissionPid) {
		this.sysPermissionPid = sysPermissionPid;
	}

	public Long getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
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

   
}
