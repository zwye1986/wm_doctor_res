package com.pinde.sci.biz.xjgl.impl;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.xjgl.IXjEduScheduleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.EduScheduleClassMapper;
import com.pinde.sci.dao.base.EduScheduleStudentMapper;
import com.pinde.sci.dao.base.EduScheduleTeacherMapper;
import com.pinde.sci.dao.xjgl.XjEduTermExtMapper;
import com.pinde.sci.enums.xjgl.XjglCourseTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduScheduleClassExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class XjEduScheduleBizImpl implements IXjEduScheduleBiz{
    @Autowired
    private EduScheduleClassMapper scheduleClassMapper;
    @Autowired
    private EduScheduleTeacherMapper scheduleTeacherMapper;
    @Autowired
    private EduScheduleStudentMapper scheduleStudentMapper;
    @Autowired
    private XjEduTermExtMapper eduTermExtMapper;

    @Override
    public EduScheduleClass readByRecordFlow(String recordFlow) {
        if(StringUtil.isBlank(recordFlow)) {
            return null;
        }
        return scheduleClassMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<EduScheduleTeacher> readByClassFlow(String scheduleClassFlow) {
        EduScheduleTeacherExample example = new EduScheduleTeacherExample();
        EduScheduleTeacherExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(scheduleClassFlow)){
            criteria.andScheduleClassFlowEqualTo(scheduleClassFlow);
        }
        return scheduleTeacherMapper.selectByExample(example);
    }

    @Override
    public List<EduScheduleStudent> readStudentsByClassFlow(String scheduleClassFlow) {
        EduScheduleStudentExample example = new EduScheduleStudentExample();
        EduScheduleStudentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(scheduleClassFlow)){
            criteria.andScheduleClassFlowEqualTo(scheduleClassFlow);
        }
        return scheduleStudentMapper.selectByExample(example);
    }

    @Override
    public List<XjEduScheduleClassExt> seachStudentClassByMap(Map<String, Object> param) {
        return eduTermExtMapper.seachStudentClassByMap(param);
    }

    @Override
    public List<EduScheduleStudent> getChosedClass(Map<String, Object> map) {
        String year = (String)map.get("year");
        String termSeason = (String)map.get("termSeason");
        String userFlow = (String)map.get("userFlow");
        EduScheduleStudentExample example = new EduScheduleStudentExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andSessionNumberEqualTo(year).andGradeTermIdEqualTo(termSeason).andUserFlowEqualTo(userFlow);
        return scheduleStudentMapper.selectByExample(example);
    }

    @Override
    public List<EduScheduleStudent> getScheduleStuClass(String year, String termSeason, String classId, String trainTypeId, String recordFlow) {
        EduScheduleStudentExample example = new EduScheduleStudentExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andSessionNumberEqualTo(year).andGradeTermIdEqualTo(termSeason).andClassIdEqualTo(classId)
                .andGradationIdEqualTo(trainTypeId).andScheduleClassFlowEqualTo(recordFlow);
        return scheduleStudentMapper.selectByExample(example);
    }

    @Override
    public boolean isChosedCourse(String recordFlow, String userFlow) {
        EduScheduleStudentExample example = new EduScheduleStudentExample();
        example.createCriteria().andScheduleClassFlowEqualTo(recordFlow).andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        List<EduScheduleStudent> dataLst = scheduleStudentMapper.selectByExample(example);
        if(null!=dataLst&&dataLst.size()>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public int submitScheduleCourse(String recordFlow, String userFlow, String status) {
        EduScheduleStudentExample example = new EduScheduleStudentExample();
        example.createCriteria().andScheduleClassFlowEqualTo(recordFlow).andUserFlowEqualTo(userFlow);
        List<EduScheduleStudent> dataLst = scheduleStudentMapper.selectByExample(example);
        int count = 0;
        EduScheduleStudent scheduleStudent = null;
        if(null != dataLst && dataLst.size() > 0){
            scheduleStudent = dataLst.get(0);
            scheduleStudent.setRecordStatus(status);
            count = scheduleStudentMapper.updateByPrimaryKeySelective(scheduleStudent);
        }else{
            scheduleStudent = new EduScheduleStudent();
            scheduleStudent.setRecordFlow(PkUtil.getUUID());
            scheduleStudent.setScheduleClassFlow(recordFlow);
            scheduleStudent.setUserFlow(userFlow);
            scheduleStudent.setRecordStatus(status);//学生选课 避免新增却未选课的情况
            scheduleStudent.setCreateTime(DateUtil.getCurrDateTime());
            scheduleStudent.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            GeneralMethod.setRecordInfo(scheduleStudent,false);
            count = eduTermExtMapper.insertScheduleCourse(scheduleStudent);
        }
        return count;
    }
    @Override
    public List<EduScheduleTeacher> queryClassTeachers(String scheduleClassFlow) {
        EduScheduleTeacherExample example = new EduScheduleTeacherExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andScheduleClassFlowEqualTo(scheduleClassFlow);
        return scheduleTeacherMapper.selectByExample(example);
    }
    @Override
    public int saveScheduleClass(EduScheduleClass schedule, String teacherNameList,String oldTeacherNameList) {
        Map<String,Object> param = new HashMap<>();
        List<String> teaList = Arrays.asList(teacherNameList.split(","));
        List<String> oldTeaList = Arrays.asList(oldTeacherNameList.split(","));
        param.put("termFlow",schedule.getTermFlow());
        param.put("classTime",schedule.getClassTime());
        param.put("classOrder",schedule.getClassOrder());
        param.put("teaList",teaList);
        if(StringUtil.isNotBlank(schedule.getRecordFlow())){
            //同一时间同一老师无法排课（排除当前排课情况）
            param.put("courseFlow",schedule.getCourseFlow());
            List<Map<String,Object>> dataMap = eduTermExtMapper.checkConflict(param);
            if(null != dataMap && dataMap.size()>0){
                return -1;
            }
            //重新洗牌
            EduScheduleTeacherExample example = new EduScheduleTeacherExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andScheduleClassFlowEqualTo(schedule.getRecordFlow());
            scheduleTeacherMapper.deleteByExample(example);
            //安排课表老师
            EduScheduleTeacher tea = new EduScheduleTeacher();
            tea.setScheduleClassFlow(schedule.getRecordFlow());
            for(int i=0;i<teaList.size();i++){
                if(StringUtil.isBlank(teaList.get(i))){
                    continue;
                }
                boolean addFlag = true;
                tea.setRecordFlow(PkUtil.getUUID());
                tea.setClassTeacherName(teaList.get(i));
                GeneralMethod.setRecordInfo(tea,true);
                scheduleTeacherMapper.insertSelective(tea);
                //生成教学审批表下的授课老师内容记录
                for(int j=0;j<oldTeaList.size();j++) {
                    if (teaList.get(i).equals(oldTeaList.get(j))) {
                        //修改时同步删除的教师课时
                        oldTeaList = new ArrayList<>(oldTeaList);
                        oldTeaList.remove(j);
                        addFlag = false;
                        break;
                    }
                }
            }
            //排课
            GeneralMethod.setRecordInfo(schedule,false);
            return scheduleClassMapper.updateByPrimaryKeySelective(schedule);
        }else{
            //同一时间同一老师无法排课
            List<Map<String,Object>> dataMap = eduTermExtMapper.checkConflict(param);
            if(null != dataMap && dataMap.size()>0){
                return -1;
            }
            String pk = PkUtil.getUUID();
            //安排课表老师
            EduScheduleTeacher tea = new EduScheduleTeacher();
            tea.setScheduleClassFlow(pk);
            for (int i = 0; i < teaList.size(); i++) {
                if(StringUtil.isBlank(teaList.get(i))){
                    continue;
                }
                tea.setRecordFlow(PkUtil.getUUID());
                tea.setClassTeacherName(teaList.get(i));
                GeneralMethod.setRecordInfo(tea, true);
                scheduleTeacherMapper.insertSelective(tea);
            }
            //排课
            schedule.setRecordFlow(pk);
            GeneralMethod.setRecordInfo(schedule,true);
            return scheduleClassMapper.insertSelective(schedule);
        }
    }
    @Override
    public int delClass(String courseTypeId,String recordFlow,String courseFlow) {
        int num = 0;
        if(XjglCourseTypeEnum.Public.getId().equals(courseTypeId)){
            EduScheduleClass schedule = scheduleClassMapper.selectByPrimaryKey(recordFlow);
            EduScheduleClassExample example = new EduScheduleClassExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSessionNumberEqualTo(schedule.getSessionNumber())
                    .andCourseFlowEqualTo(schedule.getCourseFlow()).andClassTimeEqualTo(schedule.getClassTime()).andClassOrderEqualTo(schedule.getClassOrder());
            List<EduScheduleClass> classList = scheduleClassMapper.selectByExample(example);
            for(EduScheduleClass esc : classList){
                num += scheduleClassMapper.deleteByPrimaryKey(esc.getRecordFlow());
                EduScheduleTeacherExample teaExample = new EduScheduleTeacherExample();
                teaExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andScheduleClassFlowEqualTo(esc.getRecordFlow());
                scheduleTeacherMapper.deleteByExample(teaExample);
            }
        }else{
            //删除课堂记录
            num = scheduleClassMapper.deleteByPrimaryKey(recordFlow);
            //获取课堂老师信息
            EduScheduleTeacherExample example = new EduScheduleTeacherExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andScheduleClassFlowEqualTo(recordFlow);
            //删除课堂老师
            scheduleTeacherMapper.deleteByExample(example);
        }
        return num;
    }
}
