package com.pinde.res.biz.gydxj.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.res.biz.gydxj.IGydxjTeacherBiz;
import com.pinde.res.dao.gydxj.ext.GyXjEduNoticeExtMapper;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.dao.base.EduUserChangeApplyMapper;
import com.pinde.sci.dao.base.XjglSignClassMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class GydxjTeacherBizImpl implements IGydxjTeacherBiz {

    @Resource
    private GyXjEduNoticeExtMapper eduExtMapper;
    @Resource
    private EduStudentCourseMapper stuCourseMapper;
    @Resource
    private EduUserChangeApplyMapper applyMapper;
    @Resource
    private XjglSignClassMapper signClassMapper;

    @Override
    public List<Map<String, Object>> searchCourse(String userFlow) {
        return eduExtMapper.searchCourse(userFlow);
    }

    @Override
    public List<EduCourse> qryCourseList(String courseCode) {
        return eduExtMapper.qryCourseList(courseCode);
    }

    @Override
    public List<EduScheduleClass> scheduleCourseList(String courseFlow) {
        return eduExtMapper.scheduleCourseList(courseFlow);
    }

    @Override
    public int saveStuCourseGrade(EduStudentCourse course) {
        return stuCourseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public List<Map<String, Object>> getApplyUserList(Map<String, Object> map) {
        return eduExtMapper.getApplyUserList(map);
    }

    @Override
    public int auditApplyInfo(EduUserChangeApply apply) {
        return applyMapper.updateByPrimaryKeySelective(apply);
    }

    @Override
    public EduUser searEduUser(String barCode) {
        List<EduUser> userList = eduExtMapper.searEduUser(barCode);

        return null != userList && !userList.isEmpty()?userList.get(0):null;
    }

    @Override
    public List<Map<String, Object>> scheduleCourseList2(String courseFlow,String recordFlow) {
        return eduExtMapper.scheduleCourseList2(courseFlow,recordFlow);
    }

    @Override
    public List<Map<String, Object>> qryCourseList2(Map<String, Object> map) {
        return eduExtMapper.qryCourseList2(map);
    }

    @Override
    public List<Map<String, Object>> searchSignUserList(Map<String, Object> map) {
        return eduExtMapper.searchSignUserList(map);
    }

    @Override
    public int signOutOpt(String userFlow, String recordFlow, String sign) {
        XjglSignClassExample example = new XjglSignClassExample();
        example.createCriteria().andClassFlowEqualTo(recordFlow).andUserFlowEqualTo(userFlow);
        if(sign.equals("sign")){
            if(signClassMapper.countByExample(example)>0){
                return 0;
            }
            XjglSignClass record = new XjglSignClass();
            record.setRecordFlow(PkUtil.getUUID());
            record.setUserFlow(userFlow);
            record.setClassFlow(recordFlow);
            record.setSignTime(DateUtil.getCurrDateTime());
            record.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            return signClassMapper.insertSelective(record);
        }else{

            return signClassMapper.deleteByExample(example);
        }
    }
}
