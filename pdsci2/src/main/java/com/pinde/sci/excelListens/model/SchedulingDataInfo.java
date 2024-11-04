package com.pinde.sci.excelListens.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/10/26/17:00
 * @Description:
 */
@Data
public class SchedulingDataInfo implements Serializable {

    public SchedulingDataInfo(){
        this.disable = false;
        this.type = "input";
    }

    private String id;

    private Integer index;

    private String indexName;

    private String deptType;

    private String schStartDate;

    private String schEndDate;

    private String name;

    //页面组件类型，输入框还是下拉框
    private String type;

    private List<SelectItem> selectData;

    private String concatMon;

    private Boolean disable;

    private String isRequired;


    public String getIndexName() {
        if (null == index) {
            return "";
        }
        if (0==index) {
            return "学员姓名";
        }
        if (1==index) {
            return "身份证号";
        }
        if (2==index) {
            return "专业";
        }
        if (3==index) {
            return "年级";
        }
        if (4==index) {
            return "年限";
        }


        return indexName;
    }

}
