package com.pinde.res.biz.gydxj.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gydxj.IGydxjAdminBiz;
import com.pinde.res.dao.gydxj.ext.GyXjEduNoticeExtMapper;
import com.pinde.sci.dao.base.EduCourseMapper;
import com.pinde.sci.dao.base.NydsOfficialDoctorMapper;
import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduStudentCourse;
import com.pinde.sci.model.mo.EduTerm;
import com.pinde.sci.model.mo.NydsOfficialDoctor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class GydxjAdminBizImpl implements IGydxjAdminBiz {

    @Resource
    private GyXjEduNoticeExtMapper eduExtMapper;
    @Resource
    private NydsOfficialDoctorMapper offiMapper;
    @Resource
    private EduCourseMapper courseMapper;

    @Override
    public List<Map<String, Object>> searchEduUser(Map<String, Object> map) {
        return eduExtMapper.searchEduUser(map);
    }

    @Override
    public int backConfirm(String userFlow, String partId) {
        return eduExtMapper.backConfirm(userFlow,partId);
    }

    @Override
    public List<Map<String, Object>> getGradeAuditStus(Map<String, Object> map) {
        return eduExtMapper.getGradeAuditStus(map);
    }

    @Override
    public int auditGrade(String recordFlow, String auditStatusId) {
        return eduExtMapper.auditGrade(recordFlow,auditStatusId);
    }

    @Override
    public List<EduCourse> searchCourseList(Map<String, Object> map) {
        return eduExtMapper.searchCourseList(map);
    }

    @Override
    public List<EduTerm> searchTermList(Map<String, Object> map) {
        return eduExtMapper.searchTermList(map);
    }

    @Override
    public List<NydsOfficialDoctor> searchTutorList(Map<String, Object> map) {
        return eduExtMapper.searchTutorList(map);
    }

    @Override
    public NydsOfficialDoctor searchTutorByFlow(String doctorFlow) {
        return offiMapper.selectByPrimaryKey(doctorFlow);
    }

    @Override
    public int saveTutor(NydsOfficialDoctor tutor) {
        if(StringUtil.isNotBlank(tutor.getDoctorFlow())){
            return offiMapper.updateByPrimaryKeySelective(tutor);
        }
        return 0;
    }

    @Override
    public EduCourse searchCourseByFlow(String courseFlow) {
        return courseMapper.selectByPrimaryKey(courseFlow);
    }

    @Override
    public int saveCourse(EduCourse course) {
        if(StringUtil.isNotBlank(course.getCourseFlow())){
            return courseMapper.updateByPrimaryKeySelective(course);
        }
        return 0;
    }

    @Override
    public List<EduStudentCourse> searchStuCourseListByFlow(String userFlow) {
        return eduExtMapper.searchStuCourseListByFlow(userFlow);
    }

    @Override
    public int courseByYear(String courseSession) {
        return eduExtMapper.courseByYear(courseSession);
    }

    @Override
    public int termByYear(String sessionNumber) {
        return eduExtMapper.termByYear(sessionNumber);
    }
}
