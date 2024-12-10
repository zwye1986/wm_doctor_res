package com.pinde.sci.biz.sys;

import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUserDept;
import com.pinde.sci.model.mo.SchAndStandardDeptCfg;
import com.pinde.sci.model.mo.SysDeptExample;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IDeptBiz {
    List<SysDept> selectByExample(SysDeptExample example);

    List<SysDept> selectByDeptId(String rotationFlow,String orgFlow);
    List<SchAndStandardDeptCfg> getSchDeptByBzIds(List<String> bzDeptIds,List<String> orgFlows,String orgFlow);

    List<String> relToMeOrgFlow(String myOrgFlow);
    SysDept readSysDept(String sysDeptFlow);

    int saveDept(SysDept sysDept);

    int saveDept2(SysDept sysDept, HttpServletRequest request);

    List<SysDept> searchDept(SysDept sysDept);

    void saveOrder(String[] deptFlow);

    List<SysDept> searchDeptByOrg(String orgFlow);

    /**
     * 根据流水集合获取部门列表
     *
     * @param deptFlows
     * @return
     */
    List<SysDept> searchDeptByFlows(List<String> deptFlows);

    List<SysDept> searchDeptByNameAndFlow(String deptName, String orgFlow);

    /**
     * 根据rec和process的情况获取部门
     *
     * @param map
     * @return
     */
    List<SysDept> getDeptByRec(Map<String, Object> map);
    List<SysDept> getDeptByRecForUni(Map<String, Object> map);
//	List<SysDept> searOrgFlowDeptList(SysOrg org,String deptName);
    /**
     * 查询某用户所属部门
     */
    List<SysUserDept> searchByUserFlow(String userFlow);

    List<SysUserDept> searchByUserFlow(String userFlow,String orgFlow);

    List<Map<String,String>> searchDeptByUnion(SysDept dept, String isUnion);

    SysDept readSysDeptByName(String orgFlow, String deptName);
    //查询科主任所属科室
    List<Map<String,Object>> queryDeptListByFlow(String userFlow);

    /**
     * 医院角色科室信息维护
     * 查询科室功能（涉及到sys_dept,sch_dept,Sch_And_Standard_Dept_Cfg）
     * @param paramMap
     * @return
     */
    List<Map<String,String>> searchDeptByExt(Map<String,Object> paramMap);

    int importDeptFromExcel(MultipartFile file,String orgFlow);

    List<Map<String, Object>> searchDeptByDoctor(String userFlow, String orgFlow);

    List<SysDept> searchDeptBySpe(String resTrainingSpeId, String orgFlow);

    int deleteDeptByKey(String deptFlow);
}
