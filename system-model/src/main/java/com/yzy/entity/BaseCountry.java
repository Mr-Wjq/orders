package com.yzy.entity;

import javax.persistence.*;

@Table(name = "base_country")
public class BaseCountry {
    @Id
    private Integer id;

    /**
     * 父及地区关系
     */
    private Integer pid;

    /**
     * 地区名称
     */
    private String country;

    /**
     * 行政区划代码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取父及地区关系
     *
     * @return pid - 父及地区关系
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父及地区关系
     *
     * @param pid 父及地区关系
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取地区名称
     *
     * @return country - 地区名称
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置地区名称
     *
     * @param country 地区名称
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取行政区划代码
     *
     * @return area_code - 行政区划代码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置行政区划代码
     *
     * @param areaCode 行政区划代码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}