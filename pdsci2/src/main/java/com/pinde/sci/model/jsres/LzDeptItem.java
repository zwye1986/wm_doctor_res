package com.pinde.sci.model.jsres;

import lombok.Data;

import java.io.Serializable;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/09/19/16:24
 * @Description: 轮转科室对应的标准科室及其轮转信息
 */
@Data
public class LzDeptItem implements Serializable {

    /**
     * 轮转科室id
     * */
    private String deptFlow;

    /**
     * 轮转科室name
     * */
    private String deptName;

    /**
     * 轮转科室所属orgFlow
     * */
    private String orgFlow;

    /**
     * 轮转科室所属机构名 orgName;
     * */
    private String orgName;

    /**
     * 标准科室code id
     * */
    private String bzDeptCode;

    /**
     * 标准科室name
     * */
    private String bzDeptName;

    /**
     * 方案中标准科室对应的轮转时长，单位月
     * */
    private Double bzSchMonth;

    /**
     * 该标准科室是否是必轮科室：false-非必轮 true-必轮
     * */
    private Boolean requrstFlag;

    /**
     * 排序 本机构下的为0
     * */
    private Integer sortNo;




}
