package com.pinde.sci.enums.srm;


import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;


public enum SrmIrbTypeEnum implements GeneralEnum<String> {

    Init("Init","初始审查"),
    Retrial("Retrial","复审"),
    Revise("Revise","修正案审查"),
    Schedule("Schedule","年度定期跟踪审查"),
    Sae("Sae","严重不良事件审查"),
    Violate("Violate","违背方案审查"),
    Terminate("Terminate","暂停/终止研究审查"),
    Finish("Finish","研究完成审查"),
    ;

    private final String id;
    private final String name;

    SrmIrbTypeEnum(String id,String name) {
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


    public static String getNameById(String id) {
        return EnumUtil.getById(id, SrmIrbTypeEnum.class).getName();
    }

}


