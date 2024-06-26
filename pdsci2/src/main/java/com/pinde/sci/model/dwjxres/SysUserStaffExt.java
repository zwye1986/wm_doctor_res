package com.pinde.sci.model.dwjxres;

import com.pinde.sci.model.mo.StuDeptOfStaff;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;

/**
 * Created by pdkj on 2017/5/31.
 */
public class SysUserStaffExt extends SysUser {
    private List<StuDeptOfStaff> stuDeptOfStaffList;

    public List<StuDeptOfStaff> getStuDeptOfStaffList() {
        return stuDeptOfStaffList;
    }

    public void setStuDeptOfStaffList(List<StuDeptOfStaff> stuDeptOfStaffList) {
        this.stuDeptOfStaffList = stuDeptOfStaffList;
    }
}
