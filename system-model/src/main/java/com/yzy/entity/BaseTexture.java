package com.yzy.entity;

import javax.persistence.*;

@Table(name = "base_texture")
public class BaseTexture {
    @Id
    private Long id;

    /**
     * 材质名称
     */
    @Column(name = "texture_name")
    private String textureName;

    /**
     * 治疗类型ID
     */
    @Column(name = "base_cure_id")
    private Long baseCureId;

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
     * 获取材质名称
     *
     * @return texture_name - 材质名称
     */
    public String getTextureName() {
        return textureName;
    }

    /**
     * 设置材质名称
     *
     * @param textureName 材质名称
     */
    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }

    /**
     * 获取治疗类型ID
     *
     * @return base_cure_id - 治疗类型ID
     */
    public Long getBaseCureId() {
        return baseCureId;
    }

    /**
     * 设置治疗类型ID
     *
     * @param baseCureId 治疗类型ID
     */
    public void setBaseCureId(Long baseCureId) {
        this.baseCureId = baseCureId;
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