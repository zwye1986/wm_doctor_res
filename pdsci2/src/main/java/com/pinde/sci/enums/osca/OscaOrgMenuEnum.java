package com.pinde.sci.enums.osca;

import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum OscaOrgMenuEnum implements GeneralEnum<String> {
    ProjectMenu("ProjectMenu","考核项目管理"),
    ScoreMenu("ScoreMenu","评分表单配置"),
    ExamMenu("ExamMenu","考官信息管理");
    private final String id;
    private final String name;

    OscaOrgMenuEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, OscaOrgMenuEnum.class).getName();
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
