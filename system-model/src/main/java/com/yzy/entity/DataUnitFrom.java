package com.yzy.entity;

import java.math.BigDecimal;

import javax.persistence.*;

@Table(name = "data_unit_from")
public class DataUnitFrom {
    @Id
    private Long id;

    /**
     * 单位来源
     */
    @Column(name = "from_name")
    private String fromName;

    /**
     * 订单服务费
     */
    private BigDecimal fuwufei;

    /**
     * 0删除1正常
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
     * 获取单位来源
     *
     * @return from_name - 单位来源
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * 设置单位来源
     *
     * @param fromName 单位来源
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * 获取订单服务费
     *
     * @return fuwufei - 订单服务费
     */
    public BigDecimal getFuwufei() {
        return fuwufei;
    }

    /**
     * 设置订单服务费
     *
     * @param fuwufei 订单服务费
     */
    public void setFuwufei(BigDecimal fuwufei) {
        this.fuwufei = fuwufei;
    }

    /**
     * 获取0删除1正常
     *
     * @return status - 0删除1正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0删除1正常
     *
     * @param status 0删除1正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}