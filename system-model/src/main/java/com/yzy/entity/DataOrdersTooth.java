package com.yzy.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "data_orders_tooth")
public class DataOrdersTooth {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "orders_num")
    private String ordersNum;

    /**
     * 牙齿编号
     */
    @Column(name = "tooth_num")
    private Integer toothNum;

    /**
     * 牙齿色号
     */
    @Column(name = "tooth_color")
    private String toothColor;

    /**
     * 产品材质id
     */
    @Column(name = "base_product_id")
    private Long baseProductId;

    /**
     * 每颗牙齿的价格
     */
    @Column(name = "tooth_price")
    private BigDecimal toothPrice;

    /**
     * 状态0:删除/1:正常
     */
    private Integer status;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单号
     *
     * @return orders_num - 订单号
     */
    public String getOrdersNum() {
        return ordersNum;
    }

    /**
     * 设置订单号
     *
     * @param ordersNum 订单号
     */
    public void setOrdersNum(String ordersNum) {
        this.ordersNum = ordersNum;
    }

    /**
     * 获取牙齿编号
     *
     * @return tooth_num - 牙齿编号
     */
    public Integer getToothNum() {
        return toothNum;
    }

    /**
     * 设置牙齿编号
     *
     * @param toothNum 牙齿编号
     */
    public void setToothNum(Integer toothNum) {
        this.toothNum = toothNum;
    }

    /**
     * 获取牙齿色号
     *
     * @return tooth_color - 牙齿色号
     */
    public String getToothColor() {
        return toothColor;
    }

    /**
     * 设置牙齿色号
     *
     * @param toothColor 牙齿色号
     */
    public void setToothColor(String toothColor) {
        this.toothColor = toothColor;
    }

    /**
     * 获取产品材质id
     *
     * @return base_product_id - 产品材质id
     */
    public Long getBaseProductId() {
        return baseProductId;
    }

    /**
     * 设置产品材质id
     *
     * @param baseProductId 产品材质id
     */
    public void setBaseProductId(Long baseProductId) {
        this.baseProductId = baseProductId;
    }

    /**
     * 获取每颗牙齿的价格
     *
     * @return tooth_price - 每颗牙齿的价格
     */
    public BigDecimal getToothPrice() {
        return toothPrice;
    }

    /**
     * 设置每颗牙齿的价格
     *
     * @param toothPrice 每颗牙齿的价格
     */
    public void setToothPrice(BigDecimal toothPrice) {
        this.toothPrice = toothPrice;
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