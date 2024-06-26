package com.pinde.sci.model.njmuedu;

import com.pinde.sci.model.mo.EduExamScore;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;

public class EduExamScoreExt extends SysUser {

//    private String majorId;
//
//    private String majorName;

    private EduUser eduUser;

    private List<EduExamScore> examScoreList;

    public EduUser getEduUser() {
        return eduUser;
    }

    public void setEduUser(EduUser eduUser) {
        this.eduUser = eduUser;
    }

//    public String getMajorId() {
//        return majorId;
//    }
//
//    public void setMajorId(String majorId) {
//        this.majorId = majorId;
//    }
//
//    public String getMajorName() {
//        return majorName;
//    }
//
//    public void setMajorName(String majorName) {
//        this.majorName = majorName;
//    }

    public List<EduExamScore> getExamScoreList() {
        return examScoreList;
    }

    public void setExamScoreList(List<EduExamScore> examScoreList) {
        this.examScoreList = examScoreList;
    }
}
