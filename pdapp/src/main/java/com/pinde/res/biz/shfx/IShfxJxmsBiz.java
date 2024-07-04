package com.pinde.res.biz.shfx;

import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface IShfxJxmsBiz {

    List<Map<String, String>> schDoctorSchProcessHead(Map<String, String> schArrangeResultMap);

    //根据角色和deptFlow获取user
    List<SysUser> teacherRoleCheckUser(String deptFlow, String role, String teaName, String userFlow);

    List<ResDoctorSchProcess> searchProcessByDoctorNew(Map<String, Object> paramMap);

    List<SysUser> searchSysUserByuserFlows(List<String> userFlows);

    List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows);

    Map<String, String> parseRecContent(String content);

    ResScore getScoreByProcess(String processFlow);

    ResDoctorSchProcess read(String processFlow);

    SchDept readSchDept(String schDeptFlow);

    int schProcessStudentDistinctQuery(String deptFlow, String userFlow, String isOpen);

    void auditDate(String userFlow, String dataFlow);

    ResDoctorSchProcess searchByResultFlow(String resultFlow);

    ResRec queryResRec(String processFlow, String operUserFlow, String recTypeId);

    int auditRecDate(String userFlow, String recFlow, String statusId);
    /**
     * 某个类型的数据信息
     * @param processFlow
     * @param docFlow
     * @param recType
     * @param biaoJi
     * @return
     */
    List<ResRec> searchRecByProcessAndRecType(String processFlow, String docFlow, String recType, String biaoJi);
    /**
     * 某个类型的数据信息
     * @param processFlow
     * @param docFlow
     * @param recType
     * @param biaoJi
     * @return
     */
    List<ResRec> searchRecByProcessAndRecTypeBatch(String processFlow, String docFlow, String recType, String biaoJi);
}