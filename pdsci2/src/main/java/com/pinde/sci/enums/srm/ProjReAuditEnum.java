package com.pinde.sci.enums.srm;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

/**
 * Created by www.0001.Ga on 2017-08-21.
 */
public enum ProjReAuditEnum implements GeneralEnum<String> {
    ReApplyAudit("ReApprove" , "申报重审"),
    ReApprove("ReApprove" , "立项重审"),
    ReContractAudit("ReApprove" , "合同重审"),
    ReScheduleAudit("ReApprove" , "进展报告重审"),
    ReChangeAudit("ReApprove" , "变更申请重审"),
    ReCompleteAudit("ReApprove" , "验收报告重审"),
    ReTerminateAudit("ReApprove" , "中止报告重审"),
    ;

    private final String id;
    private final String name;

    ProjReAuditEnum(String id,String name) {
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
        return EnumUtil.getById(id, ProjApproveStatusEnum.class).getName();
    }
}
