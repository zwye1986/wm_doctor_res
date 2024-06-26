package com.pinde.sci.enums.gzykdx;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 广州医科大学录取状态枚举
 * @since 2017/3/10
 */
public enum GzykdxAdmissionStatusEnum implements GeneralEnum<String> {
    UnPassed("UnPassed","不录取"),
    Passed("Passed","待录取"),
    Passing("Passing","待审核"),
    GiveUpPassed("GiveUpPassed","放弃待录取"),
    SchoolPassing("SchoolPassing","待学校录取");
    private final String id;
    private final String name;

    GzykdxAdmissionStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, GzykdxAdmissionStatusEnum.class).getName();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
