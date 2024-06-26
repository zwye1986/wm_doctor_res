package com.pinde.sci.webservice.bean.shzs;

/**
 * Created by yex on 2018-11-12.
 */
public class DeptInfo {
    //科室名称  长度25
    private String deptName;
    //对应的科室id 长度20
    private String deptId;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
