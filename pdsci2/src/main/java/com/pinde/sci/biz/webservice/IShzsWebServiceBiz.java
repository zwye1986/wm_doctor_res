package com.pinde.sci.biz.webservice;

import com.pinde.sci.model.mo.*;
import com.pinde.sci.webservice.bean.shzs.DeptInfo;
import com.pinde.sci.webservice.bean.shzs.DocInfo;
import com.pinde.sci.webservice.bean.shzs.DocInfoForm;
import com.pinde.sci.webservice.bean.shzs.UserInfoForm;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface IShzsWebServiceBiz {

    int saveDept(DeptInfo deptInfo);

    SysDept readDeptById(String deptID);

    SchDept readSchDeptById(String deptID);

    SysDept readDeptByName(String deptName, String orgFlow);

    int saveEditDept(DeptInfo deptInfo);

    int stopDept(DeptInfo deptInfo);

    int enableDept(DeptInfo deptInfo);

    String saveUserInfo(UserInfoForm form, List<SysDept> depts);

    int readDocCountByTea(String userFlow);

    int readDocCountByHead(String userFlow);

    int readDocProcess(String userFlow);

    String saveUserBaseInfo(UserInfoForm form);

    String saveDocInfo(DocInfoForm form, SchRotation rotation);

    SchRotation readRotationBySpeId(String trainingSpeId, String s);

    int delDocInfo(DocInfo docInfo);

    List<Map<String,String>> readRotationDeptByFlow(String rotationFlow);

    int checkUserDept(String userFlow, String deptFlow);

    List<SchArrangeResult> checkResultDate(String userFlow, String schStartDate, String schEndDate, String rotationFlow);

    int editDoctorResult(SysUser user, SchRotationDept rotationDept, SysDept sysDept, String schStartDate, String schEndDate, SysUser tea, SysUser head) throws ParseException;
}
