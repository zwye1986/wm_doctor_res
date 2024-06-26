package com.pinde.sci.model.recruit;

import com.pinde.sci.model.mo.*;

public class RecruitInfoExt extends RecruitInfo {
    private SysUser sysUser;
    private RecruitExamMain recruitExamMain;
    private RecruitExamInfo recruitExamInfo;
    private RecruitExamRoomInfo recruitExamRoomInfo;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public RecruitExamMain getRecruitExamMain() {
        return recruitExamMain;
    }

    public void setRecruitExamMain(RecruitExamMain recruitExamMain) {
        this.recruitExamMain = recruitExamMain;
    }

    public RecruitExamInfo getRecruitExamInfo() {
        return recruitExamInfo;
    }

    public void setRecruitExamInfo(RecruitExamInfo recruitExamInfo) {
        this.recruitExamInfo = recruitExamInfo;
    }

    public RecruitExamRoomInfo getRecruitExamRoomInfo() {
        return recruitExamRoomInfo;
    }

    public void setRecruitExamRoomInfo(RecruitExamRoomInfo recruitExamRoomInfo) {
        this.recruitExamRoomInfo = recruitExamRoomInfo;
    }
}
