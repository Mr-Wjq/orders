package com.yzy.entity;

import javax.persistence.*;

@Table(name = "data_unit_factory")
public class DataUnitFactory {
    /**
     * 医院/诊所ID(单位id)
     */
    @Column(name = "unit_id")
    private Long unitId;

    /**
     * 工厂id(单位id)
     */
    @Column(name = "factory_id")
    private Long factoryId;

    /**
     * 获取医院/诊所ID(单位id)
     *
     * @return unit_id - 医院/诊所ID(单位id)
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 设置医院/诊所ID(单位id)
     *
     * @param unitId 医院/诊所ID(单位id)
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取工厂id(单位id)
     *
     * @return factory_id - 工厂id(单位id)
     */
    public Long getFactoryId() {
        return factoryId;
    }

    /**
     * 设置工厂id(单位id)
     *
     * @param factoryId 工厂id(单位id)
     */
    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }
}