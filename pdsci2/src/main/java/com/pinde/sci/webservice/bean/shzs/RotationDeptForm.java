package com.pinde.sci.webservice.bean.shzs;

/**
 * Created by yex on 2018-11-17.
 */
public class RotationDeptForm {

    //token 验证码
    private String token;
    //专业
    private String trainingSpeId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTrainingSpeId() {
        return trainingSpeId;
    }

    public void setTrainingSpeId(String trainingSpeId) {
        this.trainingSpeId = trainingSpeId;
    }
}
