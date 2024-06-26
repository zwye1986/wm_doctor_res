package com.pinde.sci.form.xjgl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 硕士/博士研究生学籍登记表
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class XjPollingTableForm {

    private String educationType;//硕士、博士学历类型
    private String tutorUnit;//导师单位
    private String studentId;//学号
    private String ticketNumber;//准考证号
    private String degreeType;//学位类型
    private String userName;//姓名
    private String nameSpell;//姓名拼音
    private String sexName;//性别
    private String birthDate;//出生日期
    private String nation;//民族
    private String marriage;//婚否
    private String tutor;//导师
    private String speName;//专业
    private String grade;//年级
    private String hkInSchool;//户口是否转入学校
    private String hkType;//.户口类型
    private String headImgUrl;//头像照片
    private String nativePlaceProvince;//籍贯省
    private String nativePlaceCity;//籍贯市
    private String nativePlaceArea;//籍贯区
    private String nativePlaceCountry;//籍贯乡
    private String whetherCurrent;//是否应届
    private String  politicalOutlook;//政治面貌
    private String idNumber;//身份证号
    private String studentSourceArea;//生源地
    private String birthPlaceProvince;//生源地省
    private String birthPlacePrefectureLevelCity;//生源地地级市
    private String birthPlaceCountyLevelCity;//生源地县级市
    private String homePlace;//出生地
    private String entranceTime;//入学时间
    private String mobilePhone;//手机
    private String homeAddress;//家庭通讯地址
    private String postCode;//邮政编码
    private String homePhone;//家庭电话
    private String cultureType;//培养方式
    private String overseasChinese;//是否华侨、港澳台学生
    private String specialty;//特长
    private String finalEducationYear;//最后学历年份
    private String finalEducationSchool;//最后学历大学
    private String finalEducationCollege;//最后学历学院
    private String finalEducationDepartment;//最后学历系
    private String finalEducationSpe;//最后学历专业
    private String finalEducationDropOutYear;//最后学历休学年限
    private List<XjPollingTableEducationForm> educationList;
    private List<XjPollingTableFamilyForm> familyList;
    private List<XjPollingTableChangeForm> changeList;
    private List<XjPollingTableBonusPenaltyForm> bonusPenaltyList;

    private String nativePlaceProvinceId;//籍贯省id
    private String nativePlaceCityId;//籍贯市id
    private String nativePlaceAreaId;//籍贯区id

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public String getTutorUnit() {
        return tutorUnit;
    }

    public void setTutorUnit(String tutorUnit) {
        this.tutorUnit = tutorUnit;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameSpell() {
        return nameSpell;
    }

    public void setNameSpell(String nameSpell) {
        this.nameSpell = nameSpell;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getHkInSchool() {
        return hkInSchool;
    }

    public void setHkInSchool(String hkInSchool) {
        this.hkInSchool = hkInSchool;
    }

    public String getHkType() {
        return hkType;
    }

    public void setHkType(String hkType) {
        this.hkType = hkType;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getNativePlaceProvince() {
        return nativePlaceProvince;
    }

    public void setNativePlaceProvince(String nativePlaceProvince) {
        this.nativePlaceProvince = nativePlaceProvince;
    }

    public String getNativePlaceCity() {
        return nativePlaceCity;
    }

    public void setNativePlaceCity(String nativePlaceCity) {
        this.nativePlaceCity = nativePlaceCity;
    }

    public String getNativePlaceArea() {
        return nativePlaceArea;
    }

    public void setNativePlaceArea(String nativePlaceArea) {
        this.nativePlaceArea = nativePlaceArea;
    }

    public String getNativePlaceCountry() {
        return nativePlaceCountry;
    }

    public void setNativePlaceCountry(String nativePlaceCountry) {
        this.nativePlaceCountry = nativePlaceCountry;
    }

    public String getWhetherCurrent() {
        return whetherCurrent;
    }

    public void setWhetherCurrent(String whetherCurrent) {
        this.whetherCurrent = whetherCurrent;
    }

    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirthPlaceProvince() {
        return birthPlaceProvince;
    }

    public void setBirthPlaceProvince(String birthPlaceProvince) {
        this.birthPlaceProvince = birthPlaceProvince;
    }

    public String getBirthPlacePrefectureLevelCity() {
        return birthPlacePrefectureLevelCity;
    }

    public void setBirthPlacePrefectureLevelCity(String birthPlacePrefectureLevelCity) {
        this.birthPlacePrefectureLevelCity = birthPlacePrefectureLevelCity;
    }

    public String getBirthPlaceCountyLevelCity() {
        return birthPlaceCountyLevelCity;
    }

    public void setBirthPlaceCountyLevelCity(String birthPlaceCountyLevelCity) {
        this.birthPlaceCountyLevelCity = birthPlaceCountyLevelCity;
    }

    public String getHomePlace() {
        return homePlace;
    }

    public void setHomePlace(String homePlace) {
        this.homePlace = homePlace;
    }

    public String getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(String entranceTime) {
        this.entranceTime = entranceTime;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCultureType() {
        return cultureType;
    }

    public void setCultureType(String cultureType) {
        this.cultureType = cultureType;
    }

    public String getOverseasChinese() {
        return overseasChinese;
    }

    public void setOverseasChinese(String overseasChinese) {
        this.overseasChinese = overseasChinese;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getFinalEducationYear() {
        return finalEducationYear;
    }

    public void setFinalEducationYear(String finalEducationYear) {
        this.finalEducationYear = finalEducationYear;
    }

    public String getFinalEducationSchool() {
        return finalEducationSchool;
    }

    public void setFinalEducationSchool(String finalEducationSchool) {
        this.finalEducationSchool = finalEducationSchool;
    }

    public String getFinalEducationCollege() {
        return finalEducationCollege;
    }

    public void setFinalEducationCollege(String finalEducationCollege) {
        this.finalEducationCollege = finalEducationCollege;
    }

    public String getFinalEducationDepartment() {
        return finalEducationDepartment;
    }

    public void setFinalEducationDepartment(String finalEducationDepartment) {
        this.finalEducationDepartment = finalEducationDepartment;
    }

    public String getFinalEducationSpe() {
        return finalEducationSpe;
    }

    public void setFinalEducationSpe(String finalEducationSpe) {
        this.finalEducationSpe = finalEducationSpe;
    }

    public String getFinalEducationDropOutYear() {
        return finalEducationDropOutYear;
    }

    public void setFinalEducationDropOutYear(String finalEducationDropOutYear) {
        this.finalEducationDropOutYear = finalEducationDropOutYear;
    }

    public List<XjPollingTableEducationForm> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<XjPollingTableEducationForm> educationList) {
        this.educationList = educationList;
    }

    public List<XjPollingTableFamilyForm> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<XjPollingTableFamilyForm> familyList) {
        this.familyList = familyList;
    }

    public List<XjPollingTableChangeForm> getChangeList() {
        return changeList;
    }

    public void setChangeList(List<XjPollingTableChangeForm> changeList) {
        this.changeList = changeList;
    }

    public List<XjPollingTableBonusPenaltyForm> getBonusPenaltyList() {
        return bonusPenaltyList;
    }

    public void setBonusPenaltyList(List<XjPollingTableBonusPenaltyForm> bonusPenaltyList) {
        this.bonusPenaltyList = bonusPenaltyList;
    }

    public String getNativePlaceProvinceId() {
        return nativePlaceProvinceId;
    }

    public void setNativePlaceProvinceId(String nativePlaceProvinceId) {
        this.nativePlaceProvinceId = nativePlaceProvinceId;
    }

    public String getNativePlaceCityId() {
        return nativePlaceCityId;
    }

    public void setNativePlaceCityId(String nativePlaceCityId) {
        this.nativePlaceCityId = nativePlaceCityId;
    }

    public String getNativePlaceAreaId() {
        return nativePlaceAreaId;
    }

    public void setNativePlaceAreaId(String nativePlaceAreaId) {
        this.nativePlaceAreaId = nativePlaceAreaId;
    }

    public String getStudentSourceArea() {
        return studentSourceArea;
    }

    public void setStudentSourceArea(String studentSourceArea) {
        this.studentSourceArea = studentSourceArea;
    }
}

