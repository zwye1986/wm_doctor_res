package com.pinde.sci.excelListens.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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
public class SchedulingDataModel implements java.io.Serializable {

    private String id;

    //success-color #b3ffff
    //fial-color #ff6666
    private String color = "#b3ffff";

    private String tip = "";

    //每一行的数据 key:列的下标
    //JSONObject:key value--> SchedulingDataInfo
    List<SchedulingDataInfo> cellData;

    public String getColor() {
        if (StringUtils.isNotEmpty(this.tip)) {
            return "#ff6666";
        }
        return "#b3ffff";
    }


//    public String getTip() {
//        if (CollectionUtil.isEmpty(cellData)) {
//            return "";
//        }
//        if (cellData.size()>=1) {
//            SchedulingDataInfo userNameInfo = cellData.get(0);
//            if (ObjectUtil.isEmpty(userNameInfo) || StringUtils.isEmpty(userNameInfo.getName())) {
//                this.tip = this.tip+"学员姓名不能为空！@_@";
//            }
//        }
//        if (cellData.size()>=2) {
//            SchedulingDataInfo idCardInfo = cellData.get(1);
//            if (ObjectUtil.isEmpty(idCardInfo) || StringUtils.isEmpty(idCardInfo.getName())) {
//                this.tip = this.tip+"身份证号不能为空！@_@";
//            }
//        }
//        if (cellData.size()>=3) {
//            SchedulingDataInfo speSelect = cellData.get(2);
//            if (ObjectUtil.isEmpty(speSelect) || StringUtils.isEmpty(speSelect.getName())) {
//                this.tip = this.tip+"请选择专业！@_@";
//            }
//        }
//        if (cellData.size()>=4) {
//            SchedulingDataInfo sessionNum = cellData.get(3);
//            if (ObjectUtil.isEmpty(sessionNum) || StringUtils.isEmpty(sessionNum.getName())) {
//                this.tip = this.tip+"年级不能为空！@_@";
//            }
//        }
//        if (cellData.size()>=5) {
//            SchedulingDataInfo trainYear = cellData.get(4);
//            if (ObjectUtil.isEmpty(trainYear) || StringUtils.isEmpty(trainYear.getName())) {
//                this.tip = this.tip+"年限不能为空！@_@";
//            }
//        }
//        if (StringUtils.isNotEmpty(tip)) {
//            tip = StringUtils.replace(tip, "@_@", "<br/>");
//        }
//        return tip;
//    }
}
