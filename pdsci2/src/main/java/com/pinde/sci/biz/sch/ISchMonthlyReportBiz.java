package com.pinde.sci.biz.sch;

import com.pinde.sci.model.jsres.JsDoctorInfoExt;
import com.pinde.sci.model.res.SchProcessExt;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ISchMonthlyReportBiz {
    /**
     * 计划出入科学员信息
     * @param paramMap
     * @return
     */
    List<SchProcessExt> searchPlanAccessDoc(Map<String, Object> paramMap);

    /**
     * 已入科学员信息
     * @param paramMap
     * @return
     */
    List<SchProcessExt> searchAlreadyInDoc(Map<String, Object> paramMap);

    /**
     * 上月出科和未出科学员信息
     * @param paramMap
     * @return
     */
    List<SchProcessExt> searchInAndOutDoc(Map<String, Object> paramMap);

    /**
     *
     * @param fileName
     * @param sheet1Title
     * @param sheet2Title
     * @param dataList
     * @param dataList2
     * @param response
     * @throws Exception
     */
    void export4MonthlyReport(String fileName,String sheet1Title,
                              String sheet2Title,
                              List<Map<String,String>> dataList,
                              List<Map<String,String>> dataList2,
                              HttpServletResponse response)
            throws Exception;
}
