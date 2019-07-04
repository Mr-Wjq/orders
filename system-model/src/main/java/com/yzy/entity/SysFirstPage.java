package com.yzy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_first_page")
public class SysFirstPage {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 首页名称
     */
    @Column(name = "first_page_name")
    private String firstPageName;

    /**
     * 首页路径
     */
    @Column(name = "first_page_path")
    private String firstPagePath;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 配置说明
     */
    @Column(name = "config_details")
    private String configDetails;

    /**
     * 删除标识 0：删除 1：正常
     */
    private String deflag;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取首页名称
     *
     * @return first_page_name - 首页名称
     */
    public String getFirstPageName() {
        return firstPageName;
    }

    /**
     * 设置首页名称
     *
     * @param firstPageName 首页名称
     */
    public void setFirstPageName(String firstPageName) {
        this.firstPageName = firstPageName;
    }

    /**
     * 获取首页路径
     *
     * @return first_page_path - 首页路径
     */
    public String getFirstPagePath() {
        return firstPagePath;
    }

    /**
     * 设置首页路径
     *
     * @param firstPagePath 首页路径
     */
    public void setFirstPagePath(String firstPagePath) {
        this.firstPagePath = firstPagePath;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取配置说明
     *
     * @return config_details - 配置说明
     */
    public String getConfigDetails() {
        return configDetails;
    }

    /**
     * 设置配置说明
     *
     * @param configDetails 配置说明
     */
    public void setConfigDetails(String configDetails) {
        this.configDetails = configDetails;
    }

    /**
     * 获取删除标识 0：删除 1：正常
     *
     * @return deflag - 删除标识 0：删除 1：正常
     */
    public String getDeflag() {
        return deflag;
    }

    /**
     * 设置删除标识 0：删除 1：正常
     *
     * @param deflag 删除标识 0：删除 1：正常
     */
    public void setDeflag(String deflag) {
        this.deflag = deflag;
    }
}