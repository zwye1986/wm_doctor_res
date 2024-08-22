package com.pinde.sci.enums.recruit;


import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RecruitOperEnum implements GeneralEnum<String> {
    Save("Save","保存"),
    Commit("Commit","提交"),
    Audit("Audit","审核"),
    ExamResult("ExamResult","考试结果"),
    InterView("InterView","面试通知"),
    InterViewResult("InterViewResult","面试结果"),
    Admit("Admit","录取通知"),
    AdmitResult("AdmitResult","录取结果");

    private RecruitOperEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private final String id;
    private final String name;
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
    public static String getNameById(String id) {
        return EnumUtil.getNameById(id, RecruitStatusEnum.class);
    }
}
