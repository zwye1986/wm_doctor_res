package com.pinde.sci.biz.jszy;

import com.pinde.sci.model.mo.GraduationExamResults;
import com.pinde.sci.model.mo.ResDoctorGraduationInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2017/12/27.
 */
public interface IJszyGraduationBiz {
    /**
     * 更新结业信息
     * @param resDoctorGraduationInfo
     * @return
     */
    int editGraduationInfo(ResDoctorGraduationInfo resDoctorGraduationInfo);

    /**
     * 根据主键查找结业信息
     * @param recordFlow
     * @return
     */
    ResDoctorGraduationInfo findGraduationInfo(String recordFlow);

    /**
     * 根据Example查询graduationInfo
     * @param paramMap
     * @return
     */
    List<ResDoctorGraduationInfo> searchGraduationInfoByMap(Map<String, Object> paramMap);

    ResDoctorGraduationInfo findGraduationInfoByIdNo(String idNo);

    /**
     * 批量修改结业信息的状态
     * @param recordFlows
     * @param graduationInfo
     * @return
     */
    int modifyBatch(List<String> recordFlows, ResDoctorGraduationInfo graduationInfo);

    int createCertificate(ResDoctorGraduationInfo old);

    List<Map<String,Object>> getExamResults(Map<String, Object> param);

    List<Map<String, Object>> findExamBydoctorFlow(String doctorFlow,String orgFlow);

    void exportInfo(Map<String,Object> map, List<Map<String, Object>> examResults, HttpServletResponse response) throws IOException;

    GraduationExamResults getResultByFlow(String resultId);
}
