package com.pinde.sci.webservice.bean.shzs;

/**
 * Created by yex on 2018-11-17.
 */
public class ArrangeInfo {
    //用户主键
    private String userFlow;
    //带教
    private String teacherUserFlow;
    //主任
    private String headUserFlow;
    //开始日期
    private String schStartDate;
    //结束日期
    private String schEndDate;
    //标准科室代码
    private String deptCode;
    //轮转科室标识符
    private String deptId;

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getTeacherUserFlow() {
        return teacherUserFlow;
    }

    public void setTeacherUserFlow(String teacherUserFlow) {
        this.teacherUserFlow = teacherUserFlow;
    }

    public String getHeadUserFlow() {
        return headUserFlow;
    }

    public void setHeadUserFlow(String headUserFlow) {
        this.headUserFlow = headUserFlow;
    }

    public String getSchStartDate() {
        return schStartDate;
    }

    public void setSchStartDate(String schStartDate) {
        this.schStartDate = schStartDate;
    }

    public String getSchEndDate() {
        return schEndDate;
    }

    public void setSchEndDate(String schEndDate) {
        this.schEndDate = schEndDate;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
