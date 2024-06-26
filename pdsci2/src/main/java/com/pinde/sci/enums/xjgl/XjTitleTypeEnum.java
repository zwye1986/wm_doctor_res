package com.pinde.sci.enums.xjgl;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * @Copyright njpdxx.com
 * @since 2018/3/7
 */
public enum XjTitleTypeEnum implements GeneralEnum<String> {
    Radio("Radio","单选题"),
    Multiselect("Multiselect","多选题"),
    Subjective("Subjective","主观题")
    ;

    private final String id;
    private final String name;

    XjTitleTypeEnum(String id,String name) {
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
        return EnumUtil.getById(id, XjTitleTypeEnum.class).getName();
    }
}