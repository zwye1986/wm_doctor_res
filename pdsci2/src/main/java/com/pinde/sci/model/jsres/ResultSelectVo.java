package com.pinde.sci.model.jsres;

import java.io.Serializable;
import java.util.List;

/**
 * @创建人 zsq
 * @创建时间 2021/6/3
 * 描述
 */
public class ResultSelectVo implements Serializable {
    private List<DictVo> certificateColumns;    //证件类型
    private List<DictVo> nationColumns;         //民族
    private List<DictVo> nationalityColumns;    //国籍
    private List<DictVo> englishGradeColumns;   //外语等级考试类型
    private List<DictVo> englishAbilityColumns; //英语能力
    private List<DictVo> provinceColumns;       //户口所在地省行政区划
    private List<DictVo> armyTypeColumns;       //是否军队人员
    private List<DictVo> educationColumns;      //学历
    private List<DictVo> DegreeColumns;         //学位
    private List<DictVo> dcoTypeColumns;        //人员类型
    private List<DictVo> medicalColumns;        //医疗卫生机构
    private List<DictVo> orgColumns;            //派送单位
    private List<DictVo> sendSchoolColumns;     //派送学校
    private List<DictVo> hospitalAttr;          //医院属性
    private List<DictVo> HospitalCategory;      //医院类别
    private List<DictVo> BaseAttribute;         //单位性质
    private List<DictVo> BasicHealthOrg;        //基层卫生医疗机构
    private List<DictVo> GraduateSchool;        //毕业学校
    private List<DictVo> userEducation;         //学历
    private List<DictVo> graduateMajor;         //毕业专业
    private List<DictVo> PracticeType;         //执业类型

    public List<DictVo> getPracticeType() {
        return PracticeType;
    }

    public void setPracticeType(List<DictVo> practiceType) {
        PracticeType = practiceType;
    }

    public List<DictVo> getPracticeTypeid() {
        return PracticeTypeid;
    }

    public void setPracticeTypeid(List<DictVo> practiceTypeid) {
        PracticeTypeid = practiceTypeid;
    }

    private List<DictVo> PracticeTypeid;         //执业范围
    public List<DictVo> getCertificateColumns() {
        return certificateColumns;
    }

    public void setCertificateColumns(List<DictVo> certificateColumns) {
        this.certificateColumns = certificateColumns;
    }

    public List<DictVo> getNationColumns() {
        return nationColumns;
    }

    public void setNationColumns(List<DictVo> nationColumns) {
        this.nationColumns = nationColumns;
    }

    public List<DictVo> getNationalityColumns() {
        return nationalityColumns;
    }

    public void setNationalityColumns(List<DictVo> nationalityColumns) {
        this.nationalityColumns = nationalityColumns;
    }

    public List<DictVo> getEnglishGradeColumns() {
        return englishGradeColumns;
    }

    public void setEnglishGradeColumns(List<DictVo> englishGradeColumns) {
        this.englishGradeColumns = englishGradeColumns;
    }

    public List<DictVo> getEnglishAbilityColumns() {
        return englishAbilityColumns;
    }

    public void setEnglishAbilityColumns(List<DictVo> englishAbilityColumns) {
        this.englishAbilityColumns = englishAbilityColumns;
    }

    public List<DictVo> getProvinceColumns() {
        return provinceColumns;
    }

    public void setProvinceColumns(List<DictVo> provinceColumns) {
        this.provinceColumns = provinceColumns;
    }

    public List<DictVo> getArmyTypeColumns() {
        return armyTypeColumns;
    }

    public void setArmyTypeColumns(List<DictVo> armyTypeColumns) {
        this.armyTypeColumns = armyTypeColumns;
    }

    public List<DictVo> getEducationColumns() {
        return educationColumns;
    }

    public void setEducationColumns(List<DictVo> educationColumns) {
        this.educationColumns = educationColumns;
    }

    public List<DictVo> getDegreeColumns() {
        return DegreeColumns;
    }

    public void setDegreeColumns(List<DictVo> degreeColumns) {
        DegreeColumns = degreeColumns;
    }

    public List<DictVo> getDcoTypeColumns() {
        return dcoTypeColumns;
    }

    public void setDcoTypeColumns(List<DictVo> dcoTypeColumns) {
        this.dcoTypeColumns = dcoTypeColumns;
    }

    public List<DictVo> getMedicalColumns() {
        return medicalColumns;
    }

    public void setMedicalColumns(List<DictVo> medicalColumns) {
        this.medicalColumns = medicalColumns;
    }

    public List<DictVo> getOrgColumns() {
        return orgColumns;
    }

    public void setOrgColumns(List<DictVo> orgColumns) {
        this.orgColumns = orgColumns;
    }

    public List<DictVo> getSendSchoolColumns() {
        return sendSchoolColumns;
    }

    public void setSendSchoolColumns(List<DictVo> sendSchoolColumns) {
        this.sendSchoolColumns = sendSchoolColumns;
    }

    public List<DictVo> getHospitalAttr() {
        return hospitalAttr;
    }

    public void setHospitalAttr(List<DictVo> hospitalAttr) {
        this.hospitalAttr = hospitalAttr;
    }

    public List<DictVo> getHospitalCategory() {
        return HospitalCategory;
    }

    public void setHospitalCategory(List<DictVo> hospitalCategory) {
        HospitalCategory = hospitalCategory;
    }

    public List<DictVo> getBaseAttribute() {
        return BaseAttribute;
    }

    public void setBaseAttribute(List<DictVo> baseAttribute) {
        BaseAttribute = baseAttribute;
    }

    public List<DictVo> getBasicHealthOrg() {
        return BasicHealthOrg;
    }

    public void setBasicHealthOrg(List<DictVo> basicHealthOrg) {
        BasicHealthOrg = basicHealthOrg;
    }

    public List<DictVo> getGraduateSchool() {
        return GraduateSchool;
    }

    public void setGraduateSchool(List<DictVo> graduateSchool) {
        GraduateSchool = graduateSchool;
    }

    public List<DictVo> getUserEducation() {
        return userEducation;
    }

    public void setUserEducation(List<DictVo> userEducation) {
        this.userEducation = userEducation;
    }

    public List<DictVo> getGraduateMajor() {
        return graduateMajor;
    }

    public void setGraduateMajor(List<DictVo> graduateMajor) {
        this.graduateMajor = graduateMajor;
    }
}
