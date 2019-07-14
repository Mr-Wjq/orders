package com.yzy.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "data_orders_discount")
public class DataOrdersDiscount {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 订单编号
     */
    @Column(name = "orders_num")
    private String ordersNum;

    /**
     * 优惠券id
     */
    @Column(name = "data_discount_id")
    private Long dataDiscountId;

    /**
     * 优惠金额
     */
    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    /**
     * 优惠类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     */
    @Column(name = "discount_type")
    private Integer discountType;

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
     * 获取订单编号
     *
     * @return orders_num - 订单编号
     */
    public String getOrdersNum() {
        return ordersNum;
    }

    /**
     * 设置订单编号
     *
     * @param ordersNum 订单编号
     */
    public void setOrdersNum(String ordersNum) {
        this.ordersNum = ordersNum;
    }

    /**
     * 获取优惠券id
     *
     * @return data_discount_id - 优惠券id
     */
    public Long getDataDiscountId() {
        return dataDiscountId;
    }

    /**
     * 设置优惠券id
     *
     * @param dataDiscountId 优惠券id
     */
    public void setDataDiscountId(Long dataDiscountId) {
        this.dataDiscountId = dataDiscountId;
    }

    /**
     * 获取优惠金额
     *
     * @return discount_price - 优惠金额
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 设置优惠金额
     *
     * @param discountPrice 优惠金额
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 获取优惠类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     *
     * @return discount_type - 优惠类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     */
    public Integer getDiscountType() {
        return discountType;
    }

    /**
     * 设置优惠类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     *
     * @param discountType 优惠类型1:工厂优惠券2:产品材质优惠券3:平台优惠券
     */
    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }
}