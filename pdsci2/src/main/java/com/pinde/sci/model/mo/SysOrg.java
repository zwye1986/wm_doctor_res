package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

import java.util.List;

public class SysOrg extends TeachingActivitySpeakerExample.MybatisObject {
    private String orgFlow;

    private String orgCode;

    private String orgName;

    private String orgProvId;

    private String orgProvName;

    private String orgCityId;

    private String orgCityName;

    private String orgAreaId;

    private String orgAreaName;

    private String orgTypeId;

    private String orgTypeName;

    private String orgAddress;

    private String orgPhone;

    private String chargeOrgFlow;

    private String chargeOrgName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String orgZip;

    private Integer ordinal;

    private String orgLevelId;

    private String orgLevelName;

    private String isExamOrg;

    private String isExamTea;

    private String sendSchoolId;

    private String sendSchoolName;

    private String isSecondFlag;

    private String identifyNo;

    private String hospitalPassword;

    private String osceDoctorShow;

    private String osceTeacherShow;

    private String isTrainOrg;

    private String creditCode;

    private String isSubmitId;

    private String isSubmitName;

    private String checkStatusId;

    private String checkStatusName;

    private String checkReason;

    private String oldOrgName;

    private String baseCode;

    private String orgPersonInCharge;

    private String isAcc;

    private String orgInfo;

    private String parentOrgFlow;//父id;

    private String no;//序号

    private Integer trainDoctorTotal;//在培人数
    private Integer doctorSum;//住院医师人数
    private Integer masterSum;//在职专硕人数

    private String doctorRate;//住院医师使用率
    private String masterRate;//在职专硕使用率
    private String rate;//使用率


    private Integer fillNum; //填写量
    private Integer auditNum; //审核量
    private String auditRate; //审核比例
    private String avgfillNum; //平均填写量


    private String isContain ;
    private String monthDate ;
    private String recordFlow;
    private String roleFlag;
    private String province;
    private String city;
    private String university;

    private String notGraduate;
    private String graduate;


    private List<String> doctorFlowsInOrg;//当前学校在该基地的学生doctorFlow

    private List<String> orgLevelIdNotIn;

    public String getNotGraduate() {
        return notGraduate;
    }

    public void setNotGraduate(String notGraduate) {
        this.notGraduate = notGraduate;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getIsContain() {
        return isContain;
    }

    public void setIsContain(String isContain) {
        this.isContain = isContain;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(String roleFlag) {
        this.roleFlag = roleFlag;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public List<String> getDoctorFlowsInOrg() {
        return doctorFlowsInOrg;
    }

    public void setDoctorFlowsInOrg(List<String> doctorFlowsInOrg) {
        this.doctorFlowsInOrg = doctorFlowsInOrg;
    }

    public Integer getFillNum() {
        return fillNum;
    }

    public void setFillNum(Integer fillNum) {
        this.fillNum = fillNum;
    }

    public Integer getAuditNum() {
        return auditNum;
    }

    public void setAuditNum(Integer auditNum) {
        this.auditNum = auditNum;
    }

    public String getAuditRate() {
        return auditRate;
    }

    public void setAuditRate(String auditRate) {
        this.auditRate = auditRate;
    }

    public String getAvgfillNum() {
        return avgfillNum;
    }

    public void setAvgfillNum(String avgfillNum) {
        this.avgfillNum = avgfillNum;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDoctorRate() {
        return doctorRate;
    }

    public void setDoctorRate(String doctorRate) {
        this.doctorRate = doctorRate;
    }

    public String getMasterRate() {
        return masterRate;
    }

    public void setMasterRate(String masterRate) {
        this.masterRate = masterRate;
    }

    public Integer getDoctorSum() {
        return doctorSum;
    }

    public void setDoctorSum(Integer doctorSum) {
        this.doctorSum = doctorSum;
    }

    public Integer getMasterSum() {
        return masterSum;
    }

    public void setMasterSum(Integer masterSum) {
        this.masterSum = masterSum;
    }

    public Integer getTrainDoctorTotal() {
        return trainDoctorTotal;
    }

    public void setTrainDoctorTotal(Integer trainDoctorTotal) {
        this.trainDoctorTotal = trainDoctorTotal;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getParentOrgFlow() {
        return parentOrgFlow;
    }

    public void setParentOrgFlow(String parentOrgFlow) {
        this.parentOrgFlow = parentOrgFlow;
    }
    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgProvId() {
        return orgProvId;
    }

    public void setOrgProvId(String orgProvId) {
        this.orgProvId = orgProvId;
    }

    public String getOrgProvName() {
        return orgProvName;
    }

    public void setOrgProvName(String orgProvName) {
        this.orgProvName = orgProvName;
    }

    public String getOrgCityId() {
        return orgCityId;
    }

    public void setOrgCityId(String orgCityId) {
        this.orgCityId = orgCityId;
    }

    public String getOrgCityName() {
        return orgCityName;
    }

    public void setOrgCityName(String orgCityName) {
        this.orgCityName = orgCityName;
    }

    public String getOrgAreaId() {
        return orgAreaId;
    }

    public void setOrgAreaId(String orgAreaId) {
        this.orgAreaId = orgAreaId;
    }

    public String getOrgAreaName() {
        return orgAreaName;
    }

    public void setOrgAreaName(String orgAreaName) {
        this.orgAreaName = orgAreaName;
    }

    public String getOrgTypeId() {
        return orgTypeId;
    }

    public void setOrgTypeId(String orgTypeId) {
        this.orgTypeId = orgTypeId;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getChargeOrgFlow() {
        return chargeOrgFlow;
    }

    public void setChargeOrgFlow(String chargeOrgFlow) {
        this.chargeOrgFlow = chargeOrgFlow;
    }

    public String getChargeOrgName() {
        return chargeOrgName;
    }

    public void setChargeOrgName(String chargeOrgName) {
        this.chargeOrgName = chargeOrgName;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }

    public String getOrgZip() {
        return orgZip;
    }

    public void setOrgZip(String orgZip) {
        this.orgZip = orgZip;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getOrgLevelId() {
        return orgLevelId;
    }

    public void setOrgLevelId(String orgLevelId) {
        this.orgLevelId = orgLevelId;
    }

    public String getOrgLevelName() {
        return orgLevelName;
    }

    public void setOrgLevelName(String orgLevelName) {
        this.orgLevelName = orgLevelName;
    }

    public String getIsExamOrg() {
        return isExamOrg;
    }

    public void setIsExamOrg(String isExamOrg) {
        this.isExamOrg = isExamOrg;
    }

    public String getIsExamTea() {
        return isExamTea;
    }

    public void setIsExamTea(String isExamTea) {
        this.isExamTea = isExamTea;
    }

    public String getSendSchoolId() {
        return sendSchoolId;
    }

    public void setSendSchoolId(String sendSchoolId) {
        this.sendSchoolId = sendSchoolId;
    }

    public String getSendSchoolName() {
        return sendSchoolName;
    }

    public void setSendSchoolName(String sendSchoolName) {
        this.sendSchoolName = sendSchoolName;
    }

    public String getIsSecondFlag() {
        return isSecondFlag;
    }

    public void setIsSecondFlag(String isSecondFlag) {
        this.isSecondFlag = isSecondFlag;
    }

    public String getIdentifyNo() {
        return identifyNo;
    }

    public void setIdentifyNo(String identifyNo) {
        this.identifyNo = identifyNo;
    }

    public String getHospitalPassword() {
        return hospitalPassword;
    }

    public void setHospitalPassword(String hospitalPassword) {
        this.hospitalPassword = hospitalPassword;
    }

    public String getOsceDoctorShow() {
        return osceDoctorShow;
    }

    public void setOsceDoctorShow(String osceDoctorShow) {
        this.osceDoctorShow = osceDoctorShow;
    }

    public String getOsceTeacherShow() {
        return osceTeacherShow;
    }

    public void setOsceTeacherShow(String osceTeacherShow) {
        this.osceTeacherShow = osceTeacherShow;
    }

    public String getIsTrainOrg() {
        return isTrainOrg;
    }

    public void setIsTrainOrg(String isTrainOrg) {
        this.isTrainOrg = isTrainOrg;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getIsSubmitId() {
        return isSubmitId;
    }

    public void setIsSubmitId(String isSubmitId) {
        this.isSubmitId = isSubmitId;
    }

    public String getIsSubmitName() {
        return isSubmitName;
    }

    public void setIsSubmitName(String isSubmitName) {
        this.isSubmitName = isSubmitName;
    }

    public String getCheckStatusId() {
        return checkStatusId;
    }

    public void setCheckStatusId(String checkStatusId) {
        this.checkStatusId = checkStatusId;
    }

    public String getCheckStatusName() {
        return checkStatusName;
    }

    public void setCheckStatusName(String checkStatusName) {
        this.checkStatusName = checkStatusName;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public String getOldOrgName() {
        return oldOrgName;
    }

    public void setOldOrgName(String oldOrgName) {
        this.oldOrgName = oldOrgName;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getOrgPersonInCharge() {
        return orgPersonInCharge;
    }

    public void setOrgPersonInCharge(String orgPersonInCharge) {
        this.orgPersonInCharge = orgPersonInCharge;
    }

    public String getIsAcc() {
        return isAcc;
    }

    public void setIsAcc(String isAcc) {
        this.isAcc = isAcc;
    }

    public String getOrgInfo() {
        return orgInfo;
    }

    public void setOrgInfo(String orgInfo) {
        this.orgInfo = orgInfo;
    }

    public List<String> getOrgLevelIdNotIn() {
        return orgLevelIdNotIn;
    }

    public void setOrgLevelIdNotIn(List<String> orgLevelIdNotIn) {
        this.orgLevelIdNotIn = orgLevelIdNotIn;
    }
}