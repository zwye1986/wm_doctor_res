package com.pinde.sci.webservice.bean.shzs;

/**
 * Created by yex on 2018-11-17.
 */
public class DocInfo {
    //用户主键
    private String userFlow;
    //用户名
    private String userCode;
    //密码
    private String passWord;
    //姓名
    private String userName;
    //手机号
    private String userPhone;
    //证件号
    private String idNo;
    //年级
    private String sessionNumber;
    //专业
    private String trainingSpeId;
    //学员类型
    private String doctorTypeId;
    //培训年限
    private String trainingYears;

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getTrainingSpeId() {
        return trainingSpeId;
    }

    public void setTrainingSpeId(String trainingSpeId) {
        this.trainingSpeId = trainingSpeId;
    }

    public String getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(String doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }

    public String getTrainingYears() {
        return trainingYears;
    }

    public void setTrainingYears(String trainingYears) {
        this.trainingYears = trainingYears;
    }
}
