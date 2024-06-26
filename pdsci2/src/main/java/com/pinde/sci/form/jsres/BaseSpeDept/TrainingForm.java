package com.pinde.sci.form.jsres.BaseSpeDept;

import java.io.Serializable;
import java.util.List;

public class TrainingForm implements Serializable {

    private static final long serialVersionUID = -3081738391574662552L;
    private String lzcws;           //轮转管床数
    private String rmzl;            //日门诊量
    private String rjzl;            //日急诊量
    private String lzbx;            //轮转必选科室手写系统病历数
    private String zrs;             //3年招收人数
    private String zprs;            //当前在培人数
    private String dj;              //住院医师规范化培训登记手册
    private String kh;              //住院医师规范化培训考核手册

    private List<TrainingActivityForm> trainingActivityForms;    //培训活动记录

    public String getLzcws() {
        return lzcws;
    }

    public void setLzcws(String lzcws) {
        this.lzcws = lzcws;
    }

    public String getRmzl() {
        return rmzl;
    }

    public void setRmzl(String rmzl) {
        this.rmzl = rmzl;
    }

    public String getRjzl() {
        return rjzl;
    }

    public void setRjzl(String rjzl) {
        this.rjzl = rjzl;
    }

    public String getLzbx() {
        return lzbx;
    }

    public void setLzbx(String lzbx) {
        this.lzbx = lzbx;
    }

    public String getZrs() {
        return zrs;
    }

    public void setZrs(String zrs) {
        this.zrs = zrs;
    }

    public String getZprs() {
        return zprs;
    }

    public void setZprs(String zprs) {
        this.zprs = zprs;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public List<TrainingActivityForm> getTrainingActivityForms() {
        return trainingActivityForms;
    }

    public void setTrainingActivityForms(List<TrainingActivityForm> trainingActivityForms) {
        this.trainingActivityForms = trainingActivityForms;
    }
}
