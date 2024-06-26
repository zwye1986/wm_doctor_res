package com.pinde.sci.biz.lcjn;


import com.pinde.sci.model.mo.LcjnCourseInfo;
import com.pinde.sci.model.mo.LcjnCourseTime;
import com.pinde.sci.model.mo.LcjnDoctorCourse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ILcjnDoctorOrderInfoBiz {

    /**
     * 查询学员预约信息
     * @param map
     * @return
     */
    List<Map<String,Object>> selectDoctorOrderInfoList(Map<String, String> map);

    /**
     * 批量审核预约信息
     * @param recordFlow
     * @param auditStatusId
     * @param reason
     * @return
     */
    int batchAuditInfo(String recordFlow, String auditStatusId, String reason);

    /**
     * 查询本院学员信息
     * @param orgFlow
     * @return
     */
    List<Map<String,Object>> selectLocalDoctors(String orgFlow);

    /**
     * 统计一门课程预约人数
     * @param courseFlow
     * @return
     */
    int countOrderNum(String courseFlow);

    /**
     * 校验学员预约时间是否重复
     * @param doctorFlow
     * @param courseFlow
     * @return
     */
    int countOrderTime(String doctorFlow, String courseFlow);

    /**
     * 校验时间重复且状态待审核
     * @param doctorFlow
     * @param courseFlow
     * @param trainStartTime
     * @param trainEndTime
     * @return
     */
    List<String> selectOrderAndPassing(String doctorFlow, String courseFlow,String trainStartTime, String trainEndTime);

    /**
     * 根据courseFlow查培训时间
     * @param courseFlow
     * @return
     */
    List<LcjnCourseTime> selectCourseTime(String courseFlow);

    int updateDoctorCourse(LcjnDoctorCourse lcjnDoctorCourse,List<String> courseFlows);

    /**
     * 根据courseFlow和doctorFlow查找学员课程预约信息
     * @param courseFlow
     * @param doctorFlow
     * @return
     */
    List<LcjnDoctorCourse> selectDoctorCourse(String courseFlow,String doctorFlow);

    /**
     * 添加课程预约信息
     * @param lcjnDoctorCourse
     */
    void addDoctorCourse(LcjnDoctorCourse lcjnDoctorCourse);

    /**
     * 查询学员成绩列表
     * @param courseFlow
     * @param doctorName
     * @param enteringStatusId
     * @return
     */
    List<Map<String,Object>> selectDoctorScoreList(String courseFlow,String doctorName,String enteringStatusId,String doctorFlow);

    int editDoctorCourse(LcjnDoctorCourse lcjnDoctorCourse);

    int editCourseInfo(LcjnCourseInfo lcjnCourseInfo);

    /**
     * 导入成绩
     * @param file
     */
    void importScoreFromExcel(MultipartFile file,String courseFlow);

}
