package com.pinde.sci.enums.gzykdx;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 调剂批次枚举
 * @since 2017/3/10
 */
public enum GzykdxRecruitBatchEnum implements GeneralEnum<String> {
    InOrg("InOrg","院内调剂"),
    InSchool("InSchool","校内调剂");
    private final String id;
    private final String name;

    GzykdxRecruitBatchEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, GzykdxRecruitBatchEnum.class).getName();
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
