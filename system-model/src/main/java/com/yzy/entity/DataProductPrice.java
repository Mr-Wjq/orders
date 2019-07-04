package com.yzy.entity;

import javax.persistence.*;

@Table(name = "data_product_price")
public class DataProductPrice {
    @Id
    private Long id;

    /**
     * 工厂id
     */
    @Column(name = "factory_id")
    private Long factoryId;

    /**
     * 产品id
     */
    @Column(name = "base_product_id")
    private Long baseProductId;

    /**
     * 价格
     */
    private String price;

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
     * 获取工厂id
     *
     * @return factory_id - 工厂id
     */
    public Long getFactoryId() {
        return factoryId;
    }

    /**
     * 设置工厂id
     *
     * @param factoryId 工厂id
     */
    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 获取产品id
     *
     * @return base_product_id - 产品id
     */
    public Long getBaseProductId() {
        return baseProductId;
    }

    /**
     * 设置产品id
     *
     * @param baseProductId 产品id
     */
    public void setBaseProductId(Long baseProductId) {
        this.baseProductId = baseProductId;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(String price) {
        this.price = price;
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