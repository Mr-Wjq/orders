package com.yzy.entity;

import javax.persistence.*;

@Table(name = "data_kousaoyi_unit")
public class DataKousaoyiUnit {
    @Id
    private Long id;

    @Column(name = "data_kousaoyi_id")
    private Long dataKousaoyiId;

    @Column(name = "sys_unit_id")
    private Long sysUnitId;

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
     * @return data_kousaoyi_id
     */
    public Long getDataKousaoyiId() {
        return dataKousaoyiId;
    }

    /**
     * @param dataKousaoyiId
     */
    public void setDataKousaoyiId(Long dataKousaoyiId) {
        this.dataKousaoyiId = dataKousaoyiId;
    }

    /**
     * @return sys_unit_id
     */
    public Long getSysUnitId() {
        return sysUnitId;
    }

    /**
     * @param sysUnitId
     */
    public void setSysUnitId(Long sysUnitId) {
        this.sysUnitId = sysUnitId;
    }
}