package com.pinde.res.biz.stdp;

import com.pinde.core.model.DeptTeacherGradeInfo;
import com.pinde.core.model.ResAssessCfg;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorSchProcess;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IResGradeBiz {

    DeptTeacherGradeInfo getRecByRecType(String processFlow, String funcFlow);

    //获取医师轮转计划
    <T> T searcheDocResult(String doctorFlow, String resultFlow);

    ResDoctor readResDoctor(String userFlow);

    ResAssessCfg getGradeTemplate(String cfgCode);

    List<Map<String, Object>> parseAssessCfg(String content);

    List<Map<String, String>> parseDocGradeXml(String content);

    ResDoctorSchProcess getProcessByResult(String resultFlow);

    //江苏西医

    DeptTeacherGradeInfo getGradeRec(String userFlow, String deptFlow,
                                     String sysDeptFlow, String id);

    DeptTeacherGradeInfo getRec(String recFlow);

    List<ResAssessCfg> getAssCfg(String cfgCodeId);

    String saveGrade(String userFlow, String deptFlow, String subDeptFlow, String assessType, HttpServletRequest request);

    ResDoctorSchProcess getProcessByResultFlow(String resultFlow);

    Map<String, String> parseRecContent(String recContent);

    List<DeptTeacherGradeInfo> searchProssFlowRec(String userFlow);
    List<DeptTeacherGradeInfo> searchDeptFlowRec(String deptFlow, String isOpen);
    String getCfgByCode(String code);
    DeptTeacherGradeInfo readByFlow(String recFlow);

    Map<String,Object> parseGradeInfoXml(String recContent);

    /**
     * 根据条件查找
     * @param itemsMap
     * @return
     */
    List<DeptTeacherGradeInfo> searchResGradeByItems(Map<String,Object> itemsMap);

    /**
     * 对saveGrade新增了两个参数,在保存的过程中将原本对表单检验的表单获取调整为参数传入的方式
     * @param assessCfg
     * @param cfgFlow
     * @param userFlow
     * @param deptFlow
     * @param subDeptFlow
     * @param assessType
     * @param request
     * @return
     */
    String saveGrade1(ResAssessCfg assessCfg,String cfgFlow, String userFlow, String deptFlow, String subDeptFlow, String assessType, HttpServletRequest request);

    String saveGradeTwo(ResAssessCfg assessCfg, String cfgFlow, String userFlow, String deptFlow, String resultFlow, String assessType, HttpServletRequest request);
}