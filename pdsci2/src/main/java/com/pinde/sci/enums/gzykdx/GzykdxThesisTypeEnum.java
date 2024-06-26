package com.pinde.sci.enums.gzykdx;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 此类主要是论文类型枚举
 * @since 2017/3/7
 */
public enum GzykdxThesisTypeEnum implements GeneralEnum<String> {
    TeacherThesis("TeacherThesis","导师论文"),
    DoctorThesis("DoctorThesis","研究生论文"),
    ResearchProject("ResearchProject","科研项目");
    private final String id;
    private final String name;

    GzykdxThesisTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, GzykdxThesisTypeEnum.class).getName();
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
