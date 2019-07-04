package com.yzy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_first_page_role")
public class SysFirstPageRole {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 首页id
     */
    @Column(name = "first_page_id")
    private Long firstPageId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 删除标识 0：删除 1：正常
     */
    private String deflag;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取首页id
     *
     * @return first_page_id - 首页id
     */
    public Long getFirstPageId() {
        return firstPageId;
    }

    /**
     * 设置首页id
     *
     * @param firstPageId 首页id
     */
    public void setFirstPageId(Long firstPageId) {
        this.firstPageId = firstPageId;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
     * 获取删除标识 0：删除 1：正常
     *
     * @return deflag - 删除标识 0：删除 1：正常
     */
    public String getDeflag() {
        return deflag;
    }

    /**
     * 设置删除标识 0：删除 1：正常
     *
     * @param deflag 删除标识 0：删除 1：正常
     */
    public void setDeflag(String deflag) {
        this.deflag = deflag;
    }
}