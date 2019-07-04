package com.yzy.entity;

import javax.persistence.*;

@Table(name = "base_brand")
public class BaseBrand {
    @Id
    private Long id;

    /**
     * 品牌名称
     */
    @Column(name = "brand_name")
    private String brandName;

    /**
     * 治疗类型id
     */
    @Column(name = "base_cure_id")
    private Long baseCureId;

    /**
     * 材质id
     */
    @Column(name = "base_texture_id")
    private Long baseTextureId;

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
     * 获取品牌名称
     *
     * @return brand_name - 品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置品牌名称
     *
     * @param brandName 品牌名称
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获取治疗类型id
     *
     * @return base_cure_id - 治疗类型id
     */
    public Long getBaseCureId() {
        return baseCureId;
    }

    /**
     * 设置治疗类型id
     *
     * @param baseCureId 治疗类型id
     */
    public void setBaseCureId(Long baseCureId) {
        this.baseCureId = baseCureId;
    }

    /**
     * 获取材质id
     *
     * @return base_texture_id - 材质id
     */
    public Long getBaseTextureId() {
        return baseTextureId;
    }

    /**
     * 设置材质id
     *
     * @param baseTextureId 材质id
     */
    public void setBaseTextureId(Long baseTextureId) {
        this.baseTextureId = baseTextureId;
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