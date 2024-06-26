package com.pinde.sci.model.res;

import com.pinde.sci.model.mo.*;

import java.util.List;

public class EduUserExt extends EduUser {

    /**
     *
     */
    private static final long serialVersionUID = -1922529868589355955L;
    private SysUser sysUser;
    private SysOrg sysOrg;
    private SysRole sysRole;
    private List<EduCourse> courseList;
    private List<EduStudentCourseExt> courseExtsList;


    public List<EduStudentCourseExt> getCourseExtsList() {
        return courseExtsList;
    }

    public void setCourseExtsList(List<EduStudentCourseExt> courseExtsList) {
        this.courseExtsList = courseExtsList;
    }

    public SysOrg getSysOrg() {
        return sysOrg;
    }

    public void setSysOrg(SysOrg sysOrg) {
        this.sysOrg = sysOrg;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public List<EduCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<EduCourse> courseList) {
        this.courseList = courseList;
    }
}
