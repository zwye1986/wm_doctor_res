package com.pinde.sci.enums.gzykdx;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 此类主要是实现 XXX 功能
 * @since 2017/3/8
 */
public enum GzykdxAuditStatusEnum implements GeneralEnum<String> {
    UnPassed("UnPassed","不通过"),
    SchoolPassed("SchoolPassed","学校审核通过"),
    WaitingForSchool("WaitingForSchool","待学校审核"),
    WaitingForOrg ("WaitingForOrg","待二级机构审核"),
    WaitingForCommitted ("WaitingForCommitted","待提交"),
    Uncommitted("Uncommitted","未提交"),
    SchoolSendBack("SchoolSendBack","学校退回"),
    OrgSendBack("OrgSendBack","二级单位退回");
    private final String id;
    private final String name;

    GzykdxAuditStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(String id) {
        return EnumUtil.getById(id, GzykdxAuditStatusEnum.class).getName();
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
