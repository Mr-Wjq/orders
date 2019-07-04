package com.yzy.entity;

import javax.persistence.*;

@Table(name = "data_kousaoyi")
public class DataKousaoyi {
    @Id
    private Long id;

    @Column(name = "kousaoyi_name")
    private String kousaoyiName;

    @Column(name = "open_method")
    private String openMethod;

    /**
     * 0:删除,1:正常
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
     * @return kousaoyi_name
     */
    public String getKousaoyiName() {
        return kousaoyiName;
    }

    /**
     * @param kousaoyiName
     */
    public void setKousaoyiName(String kousaoyiName) {
        this.kousaoyiName = kousaoyiName;
    }

    /**
     * @return open_method
     */
    public String getOpenMethod() {
        return openMethod;
    }

    /**
     * @param openMethod
     */
    public void setOpenMethod(String openMethod) {
        this.openMethod = openMethod;
    }

    /**
     * 获取0:删除,1:正常
     *
     * @return status - 0:删除,1:正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0:删除,1:正常
     *
     * @param status 0:删除,1:正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}