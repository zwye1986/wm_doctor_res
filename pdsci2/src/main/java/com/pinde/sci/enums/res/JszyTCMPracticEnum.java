package com.pinde.sci.enums.res;

import com.pinde.core.commom.enums.GeneralEnum;

/**
 * Created by pdkj on 2018/1/3.
 * 由于江苏中医，中医全科部分标准科室填写的表单和其他专业有不一致
 * 次枚举用于区分中医全科哪些标准科室下安排了基层实践或者理论学习或者未安排（单选）
 */
public enum JszyTCMPracticEnum implements GeneralEnum<String> {
    N("N","无"),
    BasicPractice("BasicPractice", "基层实践"),
    TheoreticalStudy("TheoreticalStudy","理论学习"),
    ;
    private final String id;
    private final String name;

    JszyTCMPracticEnum(String id, String name) {
        this.id = id;
        this.name = name;
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
