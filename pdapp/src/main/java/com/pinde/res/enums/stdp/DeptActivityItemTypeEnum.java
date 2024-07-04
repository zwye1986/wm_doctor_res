package com.pinde.res.enums.stdp;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * 跟师学习表单状态
 */
public enum DeptActivityItemTypeEnum implements GeneralEnum<String> {
    JXCFAP("JXCFAP","教学查房安排","Dept","Y"),
    BLTLAP("BLTLAP","病例讨论安排","Dept","Y"),
    XJKAP("XJKAP","小讲课安排","Dept","Y"),
    QTHD("QTHD","其他活动","Dept","Y"),
    DSBGHAP("DSBGHAP","读书报告会安排","Dept","N"),
    CKKHAP("CKKHAP","出科考核安排","Dept","N"),
    JTBKAP("JTBKAP","集体备课安排","Scientific","N"),
    TKAP("TKAP", "听课安排","Scientific","N"),
    DDAP("DDAP","督导安排","Scientific","N"),
    SQTHD("SQTHD","其他活动","Scientific","N"),
    JYSKHAP("JYSKHAP","教研室考核安排","Scientific","N")
    ;
    private final String id;
    private final String name;
    private final String type;
    private final String isCfg;

    DeptActivityItemTypeEnum(String id, String name, String type, String isCfg) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isCfg = isCfg;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getIsCfg() {
        return isCfg;
    }

    public String getType() {
        return type;
    }
    public static String getNameById(String id) {
        return EnumUtil.getById(id, DeptActivityItemTypeEnum.class).getName();
    }
}
