package com.yzy.entity;

import javax.persistence.*;

@Table(name = "base_cure")
public class BaseCure {
    @Id
    private Long id;

    /**
     * 治疗类型名称
     */
    @Column(name = "cure_name")
    private String cureName;

    /**
     * 状态0:删除/1:正常
     */
    private Integer status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取治疗类型名称
     *
     * @return cure_name - 治疗类型名称
     */
    public String getCureName() {
        return cureName;
    }

    /**
     * 设置治疗类型名称
     *
     * @param cureName 治疗类型名称
     */
    public void setCureName(String cureName) {
        this.cureName = cureName;
    }

    /**
     * 获取状态0:删除/1:正常
     *
     * @return status - 状态0:删除/1:正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0:删除/1:正常
     *
     * @param status 状态0:删除/1:正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}