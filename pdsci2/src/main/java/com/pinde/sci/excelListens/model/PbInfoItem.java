package com.pinde.sci.excelListens.model;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/10/29/17:31
 * @Description:
 */
@Data
public class PbInfoItem implements Serializable {

    private String resultFlow;

    private String schRotationDeptFlow;
    private String arrangeFlow;

    private String doctorFlow;

    private String doctorName;

    private String sessionNumber;

    private String rotationFlow;

    private String rotationName;

    private String schDeptFlow;

    private String schDeptName;

    private String standardDeptId;

    private String standardDeptName;

    private String schStartDate;

    private String schEndDate;

    private String schMonth;

    private String deptFlow;

    private String deptName;

    //包含的月份：2024-01上,2024-01下
    private String concatMon;

    //数据来源类型：import导入的数据，db数据库存在（已排班的数据）
    private String type;

    private String recordStatus = "Y";

    private String chaiEnd;
    private String chaiNextStart;

    public String getArrangeFlow() {
        return resultFlow;
    }

    public String getSchMonth() {
        if (StringUtils.isEmpty(schStartDate) || StringUtils.isEmpty(schEndDate)) {
            return "0";
        }
        try{
            DateTime start = DateUtil.parseDate(schStartDate);
            DateTime end = DateUtil.parseDate(schEndDate);
            long l = DateUtil.betweenDay(start, end, false);
            BigDecimal divide = new BigDecimal(String.valueOf(l)).divide(new BigDecimal("30"), 1, BigDecimal.ROUND_HALF_DOWN);
            int multiply = divide.multiply(new BigDecimal("10")).setScale(0, RoundingMode.HALF_DOWN).intValue();
            int yu = multiply % 5;
            if (yu<=2) {
                double i = 0.5 * (multiply / 5);
                return String.valueOf(i);
            }else {
                double i = 0.5 * ((multiply / 5)+1);
                return String.valueOf(i);
            }
        }catch (Exception e) {
            return "0";
        }
    }

    public String getDeptFlow() {
        return this.getSchDeptFlow();
    }

    public String getDeptName() {
        return this.getSchDeptName();
    }

    public String getConcatMon() {
        if ("import".equalsIgnoreCase(type)) {
            return concatMon;
        }
        if (StringUtils.isEmpty(schStartDate) || StringUtils.isEmpty(schEndDate)) {
            return "";
        }
        try{
            List<String> result = new ArrayList<>();
            DateTime start = DateUtil.parseDate(schStartDate);
            DateTime end = DateUtil.parseDate(schEndDate);
            DateTime half = DateUtil.offsetDay(DateUtil.beginOfMonth(start), 14);
            while (start.compareTo(end)<0) {
                if (start.compareTo(half)<0) {
                    result.add(StringUtils.substring(DateUtil.formatDate(start),0,7)+"上");
                    start = half;
                }else {
                    result.add(StringUtils.substring(DateUtil.formatDate(start),0,7)+"下");
                    start = DateUtil.beginOfMonth(DateUtil.offsetMonth(start,1));
                    half = DateUtil.offsetDay(DateUtil.beginOfMonth(start), 14);
                }
            }
            return CollectionUtil.join(result,",");
        }catch (Exception e) {
            return "";
        }
    }
}
