package com.renrendai.loan.ucredit.common.model.server;

import javax.persistence.*;

@Table(name = "ms_dataapi_basedata")
public class BaseData {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String value;

    private String name;

    private Integer order;

    /**
     * 1 学历 2 关系
     */
    @Column(name = "data_type")
    private Byte dataType;

    /**
     * 默认
     */
    @Column(name = "default_type")
    private Byte defaultType;

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
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @param order
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 获取1 学历 2 关系
     *
     * @return data_type - 1 学历 2 关系
     */
    public Byte getDataType() {
        return dataType;
    }

    /**
     * 设置1 学历 2 关系
     *
     * @param dataType 1 学历 2 关系
     */
    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取默认
     *
     * @return default_type - 默认
     */
    public Byte getDefaultType() {
        return defaultType;
    }

    /**
     * 设置默认
     *
     * @param defaultType 默认
     */
    public void setDefaultType(Byte defaultType) {
        this.defaultType = defaultType;
    }
}