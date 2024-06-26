package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

import java.util.List;

public class PersonStaticExample extends SysOrg {

    //年级
    private String sessionNumber;

    //在校专硕上月在培人数1
    private Integer lastInSchoolNum;

    //在校专硕本月在培人数1
    private Integer inSchoolNum;

    //住院医师上月在培人数1
    private Integer lastResidentNum;

    //住院医师本月在培人数1
    private Integer residentNum;
    //上月两者合计1
    private Integer lastBothNum;
    //本月两者合计1
    private Integer bothNum;

    //住院医师入培人数1
    private Integer residentRecruitNum;

    //在校专硕入培人数1
    private Integer inSchoolRecruitNum;

    //住院医师待结业人数1
    private Integer residentExaminedNum;

    //在校专硕待结业人数1
    private Integer inSchoolExaminedNum;

    //住院医师结业人数1
    private Integer residentGraduatNum;

    //在校专硕结业人数1
    private Integer inSchoolGraduatNum;

    //住院医师退培人数1
    private Integer residentReturnNum;

    //在校专硕退培人数1
    private Integer inSchoolReturnNum;

    //住院医师转出人数
    private Integer residentOutNum;

    //在校专硕转出人数
    private Integer inSchoolOutNum;

    //住院医师转入人数
    private Integer residentInNum;

    //在校专硕转入人数
    private Integer inSchoolInNum;

    public Integer getLastBothNum() {
        return lastBothNum;
    }

    public void setLastBothNum(Integer lastBothNum) {
        this.lastBothNum = lastBothNum;
    }

    public Integer getBothNum() {
        return bothNum;
    }

    public void setBothNum(Integer bothNum) {
        this.bothNum = bothNum;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public Integer getLastInSchoolNum() {
        return lastInSchoolNum;
    }

    public void setLastInSchoolNum(Integer lastInSchoolNum) {
        this.lastInSchoolNum = lastInSchoolNum;
    }

    public Integer getInSchoolNum() {
        return inSchoolNum;
    }

    public void setInSchoolNum(Integer inSchoolNum) {
        this.inSchoolNum = inSchoolNum;
    }

    public Integer getLastResidentNum() {
        return lastResidentNum;
    }

    public void setLastResidentNum(Integer lastResidentNum) {
        this.lastResidentNum = lastResidentNum;
    }

    public Integer getResidentNum() {
        return residentNum;
    }

    public void setResidentNum(Integer residentNum) {
        this.residentNum = residentNum;
    }

    public Integer getResidentRecruitNum() {
        return residentRecruitNum;
    }

    public void setResidentRecruitNum(Integer residentRecruitNum) {
        this.residentRecruitNum = residentRecruitNum;
    }

    public Integer getInSchoolRecruitNum() {
        return inSchoolRecruitNum;
    }

    public void setInSchoolRecruitNum(Integer inSchoolRecruitNum) {
        this.inSchoolRecruitNum = inSchoolRecruitNum;
    }

    public Integer getResidentExaminedNum() {
        return residentExaminedNum;
    }

    public void setResidentExaminedNum(Integer residentExaminedNum) {
        this.residentExaminedNum = residentExaminedNum;
    }

    public Integer getInSchoolExaminedNum() {
        return inSchoolExaminedNum;
    }

    public void setInSchoolExaminedNum(Integer inSchoolExaminedNum) {
        this.inSchoolExaminedNum = inSchoolExaminedNum;
    }

    public Integer getResidentGraduatNum() {
        return residentGraduatNum;
    }

    public void setResidentGraduatNum(Integer residentGraduatNum) {
        this.residentGraduatNum = residentGraduatNum;
    }

    public Integer getInSchoolGraduatNum() {
        return inSchoolGraduatNum;
    }

    public void setInSchoolGraduatNum(Integer inSchoolGraduatNum) {
        this.inSchoolGraduatNum = inSchoolGraduatNum;
    }

    public Integer getResidentReturnNum() {
        return residentReturnNum;
    }

    public void setResidentReturnNum(Integer residentReturnNum) {
        this.residentReturnNum = residentReturnNum;
    }

    public Integer getInSchoolReturnNum() {
        return inSchoolReturnNum;
    }

    public void setInSchoolReturnNum(Integer inSchoolReturnNum) {
        this.inSchoolReturnNum = inSchoolReturnNum;
    }

    public Integer getResidentOutNum() {
        return residentOutNum;
    }

    public void setResidentOutNum(Integer residentOutNum) {
        this.residentOutNum = residentOutNum;
    }

    public Integer getInSchoolOutNum() {
        return inSchoolOutNum;
    }

    public void setInSchoolOutNum(Integer inSchoolOutNum) {
        this.inSchoolOutNum = inSchoolOutNum;
    }

    public Integer getResidentInNum() {
        return residentInNum;
    }

    public void setResidentInNum(Integer residentInNum) {
        this.residentInNum = residentInNum;
    }

    public Integer getInSchoolInNum() {
        return inSchoolInNum;
    }

    public void setInSchoolInNum(Integer inSchoolInNum) {
        this.inSchoolInNum = inSchoolInNum;
    }

}