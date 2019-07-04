package com.yzy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_unit_user")
public class SysUnitUser {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 单位id
     */
    @Column(name = "sys_unit_id")
    private Long sysUnitId;

    /**
     * 用户id
     */
    @Column(name = "sys_user_id")
    private Long sysUserId;

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
     * 获取单位id
     *
     * @return sys_unit_id - 单位id
     */
    public Long getSysUnitId() {
        return sysUnitId;
    }

    /**
     * 设置单位id
     *
     * @param sysUnitId 单位id
     */
    public void setSysUnitId(Long sysUnitId) {
        this.sysUnitId = sysUnitId;
    }

    /**
     * 获取用户id
     *
     * @return sys_user_id - 用户id
     */
    public Long getSysUserId() {
        return sysUserId;
    }

    /**
     * 设置用户id
     *
     * @param sysUserId 用户id
     */
    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
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
}